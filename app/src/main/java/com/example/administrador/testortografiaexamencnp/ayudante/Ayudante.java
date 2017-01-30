package com.example.administrador.testortografiaexamencnp.ayudante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasCorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasIncorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasNN;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="ortografia_cnp.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context,  context.getFilesDir() +"/"+ DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql="drop table if exists "
                + ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)

        String sql;
        sql="create table "+ ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA+ " ("+
                ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID+ " integer primary key autoincrement, "+
                ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA+" text) ";
        db.execSQL(sql);

        String sql2;
        sql2="create table "+ ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA+ " ("+
                ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas._ID+ " integer primary key autoincrement, "+
                ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.PALABRA+" text) ";

        db.execSQL(sql2);

        String sql3;
        sql3="create table "+ ContratoPalabrasNN.TablaPalabrasNN.TABLA+ " ("+
                ContratoPalabrasNN.TablaPalabrasNN._ID+ " integer primary key autoincrement, "+
                ContratoPalabrasNN.TablaPalabrasNN.CORRECTA_ID +" integer, " +
                ContratoPalabrasNN.TablaPalabrasNN.INCORRECTA_ID + " integer )";

        db.execSQL(sql3);

        //Insertamos 15 palabras de ejemplo
        /*for(int i=1; i<=15; i++)
        {
            //Generamos los datos de muestra
            String nombre = "Palabra " + i;

            //Insertamos los datos en la tabla p_correctas
            db.execSQL("INSERT INTO "+ ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA +" ("+ ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA +") " + "VALUES ('" + nombre + "')");

            //Insertamos los datos en la tabla p_incorrectas
            db.execSQL("INSERT INTO "+ ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA +" ("+ ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.PALABRA +") " + "VALUES ('" + nombre + "')");
        }*/
    }
}