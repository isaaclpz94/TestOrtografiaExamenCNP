package com.example.administrador.testortografiaexamencnp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrador.testortografiaexamencnp.POJO.Palabra;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabras;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final Uri uriP = ContratoPalabras.TablaPalabras.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvTitulooToolbar  = (TextView)findViewById(R.id.tvTituloToolbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/terminator.ttf");
        tvTitulooToolbar.setTypeface(tf);
        setSupportActionBar(toolbar);

        TextView tvDesc = (TextView)findViewById(R.id.tvDesc);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Orbitron.ttf");
        tvDesc.setTypeface(tf2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Insertar palabra de prueba
        /*ArrayList<Palabra> palabras = new ArrayList<>();
        Palabra palabra = new Palabra(0, 1, "prueba");
        palabras.add(palabra);

        Uri uri = getContentResolver().insert(uriP, palabra.getContentValues());
        uri.toString();*/

        //Columnas de la tabla a recuperar
        String[] projection = new String[] {
                ContratoPalabras.TablaPalabras.PALABRA,
                ContratoPalabras.TablaPalabras.CORRECTA};


        ContentResolver cr = getContentResolver();

        //Hacemos la consulta
        Cursor cur = cr.query(uriP,
                projection, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        if(cur.moveToFirst()){
            do{
                String palabra = cur.getString(0);
                int correcta = cur.getInt(1);
                Log.v("AQUÍ", palabra + " " + correcta);
            }while(cur.moveToNext());
        }else{
            Log.v("AQUÍ", "vacío");
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_test_examen:
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
        if (id == R.id.action_settings) {
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
