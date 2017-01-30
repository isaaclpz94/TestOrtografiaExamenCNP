package com.example.administrador.testortografiaexamencnp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrador.testortografiaexamencnp.POJO.Palabra;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasCorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasIncorrectas;
import com.example.administrador.testortografiaexamencnp.nav_test.ModoExamen;
import com.example.administrador.testortografiaexamencnp.nav_test.NavTest;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final Uri uriPI = ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.CONTENT_URI;
    private static final Uri uriPC = ContratoPalabrasCorrectas.TablaPalabrasCorrectas.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Quitamos barra de titulo de la aplicacion
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //FINDVIEWBYID & TYPEFACES
        TextView tvTituloToolbar  = (TextView)findViewById(R.id.tvTituloToolbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/terminator.ttf");
        tvTituloToolbar.setTypeface(tf);
        setSupportActionBar(toolbar);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Orbitron.ttf");
        TextView tvDesc = (TextView)findViewById(R.id.tvDesc);
        tvDesc.setTypeface(tf2);
        
        //Navigationdrawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Insertar palabra de prueba
        ArrayList<Palabra> palabras = new ArrayList<>();
        Palabra palabra1 = new Palabra(888, "prueba");
        Palabra palabra2 = new Palabra(999, "prueba2");
        Palabra palabra3 = new Palabra(0101011, "prueba3");
        palabras.add(palabra1);
        palabras.add(palabra2);
        palabras.add(palabra3);


        /*for(Palabra p: palabras) {
            Uri uri = getContentResolver().insert(uriPC, p.getContentValues());
            Log.v("uri",uri.toString());
        }*/



        //
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_test_examen:
                        Intent i = new Intent(Principal.this, NavTest.class);
                        startActivity(i);
                        break;
                    case R.id.nav_test_fallos:
                        break;
                    case R.id.nav_evolucion:
                        break;
                    case R.id.nav_compartir:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.descargar_palabras) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_test_examen) {
            // Handle the camera action
        } else if (id == R.id.nav_test_fallos) {

        } else if (id == R.id.nav_evolucion) {

        } else if (id == R.id.nav_compartir) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
