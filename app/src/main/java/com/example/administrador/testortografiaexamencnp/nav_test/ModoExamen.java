package com.example.administrador.testortografiaexamencnp.nav_test;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrador.testortografiaexamencnp.POJO.Palabra;
import com.example.administrador.testortografiaexamencnp.R;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasCorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasIncorrectas;

import java.util.ArrayList;

/**
 * Created by Administrador on 28/01/2017.
 */

public class ModoExamen extends AppCompatActivity {

    private static final Uri uriPI = ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.CONTENT_URI;
    private static final Uri uriPC = ContratoPalabrasCorrectas.TablaPalabrasCorrectas.CONTENT_URI;

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

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new AdaptadorPestañas(getSupportFragmentManager()));

        TabLayout tablayout = (TabLayout)findViewById(R.id.appbartabs);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setupWithViewPager(viewPager);

        //EXTRAEMOS LOS DATOS A AÑADIR AL RECYCLERVIEW
        //Hacemos la consulta y añadimos todas las palabras al arraylist
        String[] projection = new String[] {
                ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA,
                ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRAINCORRECTA_ID};

        ContentResolver cr = getContentResolver();

        Cursor cur = cr.query(uriPC,
                projection, //Proyección
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados
        ArrayList<Palabra> palabras = new ArrayList<>();

        assert cur != null;
        while(cur.moveToNext()){
            Palabra p = new Palabra();
            p.setPalabra(cur.getString(cur.getColumnIndex("palabra")));
            p.setPalabraincorrecta_id(cur.getInt(cur.getColumnIndex("palabraincorrecta_id")));
            palabras.add(p);
        }
        Log.v("ESTADO", palabras.size() +" PALABRAS CORRECTAS");

        //Palabras incorrectas
        String[] projection2 = new String[] {
                ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.PALABRA};

        ContentResolver cr2 = getContentResolver();

        Cursor cur2 = cr2.query(uriPI,
                projection2, //Columnas a devolver
                "_ID IN (SELECT palabraincorrecta_id FROM "+ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA+")", //Condición de la query
                //new String[]{"SELECT palabraincorrecta_id FROM "+ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRAINCORRECTA_ID},       //Argumentos variables de la query
                null,
                null);      //Orden de los resultados
        ArrayList<String> palabras2 = new ArrayList<>();

        assert cur2 != null;
        while(cur2.moveToNext()){
            palabras2.add(cur2.getString(cur2.getColumnIndex("palabra")));
        }
        Log.v("ESTADO", palabras2.size() +" PALABRAS INCORRECTAS");

        /*for (Palabra pal: palabras){
            Log.v("PALABRA", pal.toString());
        }*/

        //poblamos el recyclerview
        /*RecyclerView recView = (RecyclerView)findViewById(R.id.recView);
        recView.setHasFixedSize(true);

        final AdaptadorPrueba adaptador = new AdaptadorPrueba(palabras, palabras2);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));*/
    }
}