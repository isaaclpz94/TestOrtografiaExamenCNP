package com.example.administrador.testortografiaexamencnp.nav_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrador.testortografiaexamencnp.nav_test.fragmentos.FragmentExamen1;
import com.example.administrador.testortografiaexamencnp.nav_test.fragmentos.FragmentExamen2;

/**
 * Created by Administrador on 16/02/2017.
 */

public class AdaptadorPestañas extends FragmentPagerAdapter {

    private String[] titulos = new String[]{"Tests", "Aleatorio"};

    public AdaptadorPestañas(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch (position){
            case 0:
                f = FragmentExamen1.newInstance();
                break;
            case 1:
                f = FragmentExamen2.newInstance();
                break;
        }

        return f;
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titulos[position];
    }
}
