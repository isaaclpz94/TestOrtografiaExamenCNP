package com.example.administrador.testortografiaexamencnp.ayudante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabras;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="palabras.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context,  DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

        String sql="drop table if exists "
                + ContratoPalabras.TablaPalabras.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)

        String sql;
        sql="create table "+ ContratoPalabras.TablaPalabras.TABLA+ " ("+
                ContratoPalabras.TablaPalabras._ID+ " integer primary key autoincrement, "+
                ContratoPalabras.TablaPalabras.PALABRA+" text, "+
                ContratoPalabras.TablaPalabras.CORRECTA+" integer)";
        db.execSQL(sql);
        /*String sql2;
        sql2="create table "+ ContratoDisco.TablaDisco.TABLA+ " ("+
                ContratoDisco.TablaDisco._ID+ " integer primary key autoincrement, "+
                ContratoDisco.TablaDisco.NOMBRE+" text, "+
                ContratoDisco.TablaDisco.IDINTERPRETE+" long)";

        db.execSQL(sql2);

        String sql3;
        sql3="create table "+ ContratoInterprete.TablaInterprete.TABLA+ " ("+
                ContratoInterprete.TablaInterprete._ID+ " integer primary key autoincrement, "+
                ContratoInterprete.TablaInterprete.NOMBRE+" text)";

        db.execSQL(sql3);*/

        //Insertamos 15 palabras de ejemplo
        for(int i=1; i<=15; i++)
        {
            //Generamos los datos de muestra
            String nombre = "Palabra" + i;
            int correcta = i;

            //Insertamos los datos en la tabla Clientes
            db.execSQL("INSERT INTO "+ ContratoPalabras.TablaPalabras.TABLA +" ("+ ContratoPalabras.TablaPalabras.PALABRA +", "+ ContratoPalabras.TablaPalabras.CORRECTA +") " +
                    "VALUES ('" + nombre + "', '" + correcta +"')");
        }
    }
}