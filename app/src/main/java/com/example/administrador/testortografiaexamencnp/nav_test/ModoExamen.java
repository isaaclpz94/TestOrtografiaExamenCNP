package com.example.administrador.testortografiaexamencnp.nav_test;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrador.testortografiaexamencnp.POJO.Palabra;
import com.example.administrador.testortografiaexamencnp.R;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabras;

import java.util.ArrayList;

/**
 * Created by Administrador on 28/01/2017.
 */

public class ModoExamen extends AppCompatActivity {

    private static final Uri uriP = ContratoPalabras.TablaPalabras.CONTENT_URI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_examen);

        //FINDVIEWBYID
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvTituloToolbar = (TextView)findViewById(R.id.tvTituloToolbar);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/terminator.ttf");
        tvTituloToolbar.setTypeface(tf);

        //BOTÓN ATRÁS TOOLBAR
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //AÑADIMOS DATOS AL RECYCLERVIEW
        //Hacemos la consulta y añadimos todas las palabras al arraylist
        ArrayList<Palabra> palabras = new ArrayList<>();
        String[] projection = new String[] {
                ContratoPalabras.TablaPalabras.PALABRA,
                ContratoPalabras.TablaPalabras.CORRECTA};

        ContentResolver cr = getContentResolver();

        Cursor cur = cr.query(uriP,
                projection, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        Palabra p  = new Palabra();
        assert cur != null;
        if(cur.moveToFirst()){
            do{
                String txtpalabra = cur.getString(0);
                int correcta = cur.getInt(1);

                p.setPalabra(txtpalabra);
                p.setCorrecta(correcta);
                palabras.add(p);
            }while(cur.moveToNext());
        }

        for (Palabra pal: palabras){
            Log.v("PALABRA", pal.toString());
        }

        //poblamos el recyclerview
        RecyclerView recView = (RecyclerView)findViewById(R.id.recView);
        recView.setHasFixedSize(true);

        final AdaptadorPrueba adaptador = new AdaptadorPrueba(palabras);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
