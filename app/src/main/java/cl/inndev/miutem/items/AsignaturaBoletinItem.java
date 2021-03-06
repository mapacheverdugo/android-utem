package cl.inndev.miutem.items;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cl.inndev.miutem.R;
import cl.inndev.miutem.models.Asignatura;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class AsignaturaBoletinItem extends AbstractSectionableItem<AsignaturaBoletinItem.ViewHolder, SemestreItem> {

    private Asignatura mAsignatura;
    private Context mContext;

    public AsignaturaBoletinItem(SemestreItem header, Context context, Asignatura asignatura) {
        super(header);
        this.mAsignatura = asignatura;
        this.mContext = context;
    }


    @Override
    public boolean equals(Object inObject) {
        return this == inObject;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.view_carrera_malla_asignatura;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
        Drawable itemBorder = holder.container.getBackground();
        itemBorder = DrawableCompat.wrap(itemBorder);

        Drawable rellenoIcono = holder.iconoEstado.getBackground();
        rellenoIcono = DrawableCompat.wrap(rellenoIcono);

        holder.textCodigo.setVisibility(View.GONE);
        holder.textOportunidades.setVisibility(View.GONE);
        holder.textTipo.setVisibility(View.GONE);
        holder.container.setEnabled(false);
        holder.container.setOnClickListener(null);

        holder.textNombre.setText(mAsignatura.getNombre());
        holder.textNombre.setTypeface(null, Typeface.NORMAL);
        holder.textNota.setText(mAsignatura.getNota() == null ? null : mAsignatura.getNota().toString());

        int colorEstado;

        switch (mAsignatura.getEstado()) {
            case "A":
                colorEstado = mContext.getResources().getColor(R.color.ramo_aprobado);
                holder.layoutEstado.setVisibility(View.VISIBLE);
                holder.textEstado.setText("Aprobado");
                break;
            case "R":
                colorEstado = mContext.getResources().getColor(R.color.ramo_reprobado);
                holder.container.setEnabled(false);
                holder.container.setOnClickListener(null);
                holder.layoutEstado.setVisibility(View.VISIBLE);
                holder.textEstado.setText("Reprobado");
                break;
            case "I":
                colorEstado = mContext.getResources().getColor(R.color.ramo_inscrito);
                holder.textEstado.setText("Inscrito");
                break;
            default:
                colorEstado = 0;
                holder.container.setEnabled(false);
                holder.container.setOnClickListener(null);
                holder.container.setBackground(new ColorDrawable(Color.WHITE));
                holder.layoutEstado.setVisibility(View.GONE);
                holder.textEstado.setText(mAsignatura.getEstado());
                break;
        }

        if (colorEstado != 0) {
            DrawableCompat.setTint(itemBorder, colorEstado);
            holder.container.setBackground(itemBorder);
            DrawableCompat.setTint(rellenoIcono, colorEstado);
            holder.iconoEstado.setBackground(rellenoIcono);
            holder.textEstado.setTextColor(colorEstado);
        } else {
            DrawableCompat.setTint(itemBorder, Color.WHITE);
            holder.container.setBackground(itemBorder);
            DrawableCompat.setTint(rellenoIcono, Color.WHITE);
            holder.iconoEstado.setBackground(rellenoIcono);
            holder.textEstado.setTextColor(Color.WHITE);
        }

    }


    public class ViewHolder extends FlexibleViewHolder {

        TextView textCodigo;
        TextView textNombre;
        TextView textTipo;
        TextView textEstado;
        TextView textNota;
        TextView textOportunidades;
        View iconoEstado;
        LinearLayout layoutEstado;
        ConstraintLayout container;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            this.container = view.findViewById(R.id.container);
            layoutEstado = view.findViewById(R.id.layout_estado);
            textCodigo = view.findViewById(R.id.text_codigo);
            textNombre = view.findViewById(R.id.text_nombre);
            textTipo = view.findViewById(R.id.text_tipo);
            textEstado = view.findViewById(R.id.text_estado);
            textNota = view.findViewById(R.id.text_nota);
            textOportunidades = view.findViewById(R.id.text_oportunidades);
            iconoEstado = view.findViewById(R.id.icono_estado);
        }
    }
}