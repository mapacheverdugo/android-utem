package cl.inndev.miutem.activities;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.Objects;

import cl.inndev.miutem.R;
import cl.inndev.miutem.fragments.AsignaturaFragment;
import cl.inndev.miutem.fragments.BitacoraFragment;
import cl.inndev.miutem.fragments.NotasFragment;

public class AsignaturaActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private long mContadorVida = 0;
    private ViewPager mViewPager;
    public Integer mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura);

        mId = getIntent().getIntExtra("ASIGNATURA_ID", 0);

        if (mId == 0) {
            finish();
        }

        mViewPager = findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabLayout = findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (System.currentTimeMillis() - mContadorVida >= 60 * 1000 && mContadorVida != 0)
            finish();
        mContadorVida = 0;
    }

    @Override
    public void onStop() {
        super.onStop();
        mContadorVida = System.currentTimeMillis();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NotasFragment();
                case 1:
                    return new BitacoraFragment();
                case 2:
                    return new AsignaturaFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
