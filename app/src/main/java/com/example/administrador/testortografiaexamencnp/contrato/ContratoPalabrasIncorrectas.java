package com.example.administrador.testortografiaexamencnp.contrato;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContratoPalabrasIncorrectas {

    public ContratoPalabrasIncorrectas(){

    }

    public static abstract class TablaPalabrasIncorrectas implements BaseColumns {
        public static final String TABLA = "p_incorrectas";
        public static final String PALABRA = "palabra";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.administrador.testortografiaexamencnp.provider.ProveedorClientes";
        //Definir como llego a la tabla p_incorrectas (a q tabla estoy llegando)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME ="vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME ="vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }
}
