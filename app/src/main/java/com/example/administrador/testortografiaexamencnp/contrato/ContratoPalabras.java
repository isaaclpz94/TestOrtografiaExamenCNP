package com.example.administrador.testortografiaexamencnp.contrato;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContratoPalabras {

    public ContratoPalabras(){

    }

    public static abstract class TablaPalabras implements BaseColumns {
        public static final String TABLA = "palabras";
        public static final String PALABRA = "palabra";
        public static final String CORRECTA = "correcta";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.administrador.testortografiaexamencnp.provider.ProveedorClientes";
        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME ="vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME ="vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }
}
