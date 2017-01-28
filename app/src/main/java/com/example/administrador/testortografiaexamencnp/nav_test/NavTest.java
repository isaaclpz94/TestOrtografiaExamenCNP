package com.example.administrador.testortografiaexamencnp.nav_test;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrador.testortografiaexamencnp.R;

/**
 * Created by Administrador on 28/01/2017.
 */

public class NavTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView tvTituloToolbar = (TextView)findViewById(R.id.tvTituloToolbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/terminator.ttf");
        tvTituloToolbar.setTypeface(tf);
        setSupportActionBar(toolbar);

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

        //ONCLICK LINEAR LAYOUTS
        LinearLayout LNmodoExamen = (LinearLayout)findViewById(R.id.LNmodoExamen);
        LNmodoExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NavTest.this, ModoExamen.class);
                startActivity(i);
            }
        });

        LinearLayout LNmodoEstudio = (LinearLayout)findViewById(R.id.LNmodoEstudio);
        LNmodoEstudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}