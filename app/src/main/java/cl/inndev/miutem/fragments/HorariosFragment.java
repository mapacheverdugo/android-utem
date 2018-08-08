package cl.inndev.miutem.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import cl.inndev.miutem.R;
import cl.inndev.miutem.activities.MainActivity;
import cl.inndev.miutem.classes.Estudiante;
import cl.inndev.miutem.deserializers.HorariosDeserializer;
import cl.inndev.miutem.interfaces.ApiUtem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cl.inndev.miutem.interfaces.ApiUtem.BASE_URL;

public class HorariosFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        View view = inflater.inflate(R.layout.fragment_horario, container, false);

        getHorarios();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        // Set title bar
        mFirebaseAnalytics.setCurrentScreen(getActivity(), HorariosFragment.class.getSimpleName(),
                HorariosFragment.class.getSimpleName());
        ((MainActivity) getActivity()).setActionBarTitle("Horario");
    }

    private void getHorarios() {
        /*
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Estudiante.Horario.class, new HorariosDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiUtem client = retrofit.create(ApiUtem.class);

        Call<List<Estudiante.Horario>> call = client.getHorarios(
                Prefs.getLong("rut", 0),
                Prefs.getString("token", null)
        );

        call.enqueue(new Callback<List<Estudiante.Horario>>() {
            @Override
            public void onResponse(Call<List<Estudiante.Horario>> call, Response<List<Estudiante.Horario>> response) {
                switch (response.code()) {
                    case 200:

                        break;
                    default:
                        Toast.makeText(getContext(), "Ocurrió un error inesperado al cargar el horario", Toast.LENGTH_SHORT).show();
                        break;
                }
                //mProgressCargando.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Estudiante.Horario>> call, Throwable t) {
                Toast.makeText(getActivity(), "No se pudo cargar el horario", Toast.LENGTH_SHORT).show();
                //mProgressCargando.setVisibility(View.GONE);
            }
        });
        */
    }
}
