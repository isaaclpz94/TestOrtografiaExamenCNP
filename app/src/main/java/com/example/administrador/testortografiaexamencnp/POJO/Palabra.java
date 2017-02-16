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
    private int palabraincorrecta_id;

    public Palabra(long id, String palabra, int palabraincorrecta_id) {
        this.id = id;
        this.palabra = palabra;
        this.palabraincorrecta_id = palabraincorrecta_id;
    }

    public Palabra() {
        this(0,"",0);
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

    public int getPalabraincorrecta_id() {
        return palabraincorrecta_id;
    }

    public void setPalabraincorrecta_id(int palabraincorrecta_id) {
        this.palabraincorrecta_id = palabraincorrecta_id;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID, this.id);
        cv.put(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA, this.palabra);
        cv.put(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRAINCORRECTA_ID, this.palabraincorrecta_id);
        return cv;
    }

    public void set(Cursor c) { //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getLong(c.getColumnIndex(ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID));
        this.palabra = c.getString(c.getColumnIndex(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA));
        this.palabraincorrecta_id = c.getInt(c.getColumnIndex(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRAINCORRECTA_ID));

    }

}
