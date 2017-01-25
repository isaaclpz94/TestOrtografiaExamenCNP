package com.example.administrador.testortografiaexamencnp.POJO;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabras;

/**
 * Created by Administrador on 25/01/2017.
 */

public class Palabra {

    private long id;
    private int correcta;
    private String palabra;

    public Palabra(){
        this(0, 1,"");
    }

    public Palabra(long id, int correcta, String palabra){
        this.id=id;
        this.correcta=correcta;
        this.palabra=palabra;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoPalabras.TablaPalabras._ID,this.id);
        cv.put(ContratoPalabras.TablaPalabras.PALABRA,this.palabra);
        cv.put(ContratoPalabras.TablaPalabras.CORRECTA,this.correcta);
        return cv;
    }

    public void set(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getLong(c.getColumnIndex(ContratoPalabras.TablaPalabras._ID));
        this.palabra = c.getString(c.getColumnIndex(ContratoPalabras.TablaPalabras.PALABRA));
        this.correcta= c.getInt(c.getColumnIndex(ContratoPalabras.TablaPalabras.CORRECTA));

    }

}
