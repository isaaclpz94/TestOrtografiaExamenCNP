package com.example.administrador.testortografiaexamencnp.nav_test.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrador.testortografiaexamencnp.R;

/**
 * Created by Administrador on 16/02/2017.
 */

public class FragmentExamen1 extends Fragment {

    private View viewFragment;
    private RecyclerView recView;

    public static FragmentExamen1 newInstance(){
        FragmentExamen1 fragment = new FragmentExamen1();

        return fragment;
    }

    public FragmentExamen1(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_examen_1, container, false);
        recView = (RecyclerView)viewFragment.findViewById(R.id.recView);



        return  viewFragment;
    }
}
