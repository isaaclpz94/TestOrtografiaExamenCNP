package com.example.administrador.testortografiaexamencnp.nav_test.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrador.testortografiaexamencnp.R;

/**
 * Created by Administrador on 16/02/2017.
 */

public class FragmentExamen2 extends Fragment {

    private View viewFragment;
    private RecyclerView recView;

    public static FragmentExamen2 newInstance(){
        FragmentExamen2 fragment = new FragmentExamen2();

        return fragment;
    }

    public FragmentExamen2(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_examen_2, container, false);
        //recView = (RecyclerView)viewFragment.findViewById(R.id.recView);

        return  viewFragment;
    }

}
