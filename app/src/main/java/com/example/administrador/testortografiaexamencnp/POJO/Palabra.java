package com.example.administrador.testortografiaexamencnp.POJO;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasCorrectas;

/**
 * Created by Administrador on 25/01/2017.
 */

public class Palabra {

    private long id;
    private String palabra;

    public Palabra(){
        this(0, "");
    }

    public Palabra(long id, String palabra){
        this.id=id;
        this.palabra=palabra;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID,this.id);
        cv.put(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA,this.palabra);
        return cv;
    }

    public void set(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getLong(c.getColumnIndex(ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID));
        this.palabra = c.getString(c.getColumnIndex(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA));

    }

    @Override
    public String toString() {
        return "Palabra{" +
                "id=" + id +
                ", palabra='" + palabra + '\'' +
                '}';
    }
}
