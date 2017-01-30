package com.example.administrador.testortografiaexamencnp.contrato;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContratoPalabrasNN {

    public ContratoPalabrasNN(){

    }

    public static abstract class TablaPalabrasNN implements BaseColumns {
        public static final String TABLA = "p_nn";
        public static final String CORRECTA_ID = "correcta_id";
        public static final String INCORRECTA_ID = "incorrecta_id";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.administrador.testortografiaexamencnp.provider.ProveedorClientes";
        //Definir como llego a la tabla p_correctas (a q tabla estoy llegando)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME ="vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME ="vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }
}