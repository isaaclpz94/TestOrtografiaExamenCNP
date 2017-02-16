package com.example.administrador.testortografiaexamencnp.nav_test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrador.testortografiaexamencnp.POJO.Palabra;
import com.example.administrador.testortografiaexamencnp.R;

import java.util.ArrayList;

/**
 * Created by Administrador on 28/01/2017.
 */

public class AdaptadorPrueba extends RecyclerView.Adapter<AdaptadorPrueba.ViewHolder> {

    private ArrayList<Palabra> datos;
    private ArrayList<String> datos2;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtPalabra;
        private TextView txtPalabra2;

        public ViewHolder(View itemView) {
            super(itemView);

            txtPalabra = (TextView)itemView.findViewById(R.id.txtPalabra);
            txtPalabra2 = (TextView)itemView.findViewById(R.id.txtPalabra2);
        }

        public void bindTitular(Palabra p, String p2){
            txtPalabra.setText(p.getPalabra());
            txtPalabra2.setText(p2);
        }
    }

    public AdaptadorPrueba(ArrayList<Palabra> datos, ArrayList<String> datos2){
        this.datos = datos;
        this.datos2 = datos2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_prueba, parent, false);

        ViewHolder tvh = new ViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Palabra item = datos.get(position);
        String item2 = datos2.get(position);
        holder.bindTitular(item, item2);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
