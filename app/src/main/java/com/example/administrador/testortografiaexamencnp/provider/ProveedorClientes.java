package com.example.administrador.testortografiaexamencnp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrador.testortografiaexamencnp.ayudante.Ayudante;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasCorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasIncorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasNN;

public class ProveedorClientes extends ContentProvider {

    //La Uri es cm una URL. LLegamos a un mismo sitio. UriMatcher establece si es un 1 haz una cosa, si es 2 haz otra cosa.
    public static final UriMatcher convierteUri2Int;
    public static final int PALABRACORRECTA = 1;
    public static final int PALABRACORRECTA_ID = 2;

    public static final int PALABRAINCORRECTA = 1;
    public static final int PALABRAINCORRECTA_ID = 2;

    public static final int PALABRANN = 1;
    public static final int PALABRANN_ID = 2;


    static{
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convierteUri2Int.addURI(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.AUTHORITY, ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, PALABRACORRECTA);//Le damos la instrucción de qué hacer a la URI
        convierteUri2Int.addURI(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.AUTHORITY, ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA + "/#", PALABRACORRECTA_ID);

        convierteUri2Int.addURI(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.AUTHORITY, ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA, PALABRAINCORRECTA);//Le damos la instrucción de qué hacer a la URI
        convierteUri2Int.addURI(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.AUTHORITY, ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA + "/#", PALABRAINCORRECTA_ID);

        convierteUri2Int.addURI(ContratoPalabrasNN.TablaPalabrasNN.AUTHORITY, ContratoPalabrasNN.TablaPalabrasNN.TABLA, PALABRANN);//Le damos la instrucción de qué hacer a la URI
        convierteUri2Int.addURI(ContratoPalabrasNN.TablaPalabrasNN.AUTHORITY, ContratoPalabrasNN.TablaPalabrasNN.TABLA + "/#", PALABRANN_ID);
    }

    private Ayudante abd;

    @Override
    public boolean onCreate() {
        abd = new Ayudante(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {//Devuelve el tipo mime que corresponde a la uri con que se ha llamado
        switch (convierteUri2Int.match(uri)){
            case PALABRACORRECTA:
                if(uri.toString().contains("p_correcta")){
                    return ContratoPalabrasCorrectas.TablaPalabrasCorrectas.MJLTIPLE_MIME;
                }else if(uri.toString().contains("p_incorrecta")){
                    return ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.MJLTIPLE_MIME;
                }else if (uri.toString().contains("p_nn")){
                    return ContratoPalabrasNN.TablaPalabrasNN.MJLTIPLE_MIME;
                }
            case PALABRACORRECTA_ID:
                if(uri.toString().contains("p_correcta")){
                    return ContratoPalabrasCorrectas.TablaPalabrasCorrectas.SINGLE_MIME;
                }else if(uri.toString().contains("p_incorrecta")){
                    return ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.SINGLE_MIME;
                }else if (uri.toString().contains("p_nn")){
                    return ContratoPalabrasNN.TablaPalabrasNN.SINGLE_MIME;
                }
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }

    //METODO INSERT
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Comprobar que la uri utilizada para hacer la insercion es correcta
        if (convierteUri2Int.match(uri) != PALABRACORRECTA || convierteUri2Int.match(uri) != PALABRAINCORRECTA || convierteUri2Int.match(uri) != PALABRANN) {
            throw new IllegalArgumentException("URI desconocida : " + uri);//Si no es correcta la Uri
        }

        //Si el ContentValues es nulo, crea un ContentValues
        ContentValues contentValues;
        if (values == null) {
            throw new IllegalArgumentException("content values null ");
        }
        //Validar

        // Inserción de nueva fila
        SQLiteDatabase db = abd.getWritableDatabase();//Conectamos a la base de datos en modo escritura
        long rowId=0;
        if(uri.toString().contains("p_correcta")){
             rowId = db.insert(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, null, values);
        }else if(uri.toString().contains("p_incorrecta")){
             rowId = db.insert(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA, null, values);
        }else if (uri.toString().contains("p_nn")){
             rowId = db.insert(ContratoPalabrasNN.TablaPalabrasNN.TABLA, null, values);
        }

        if (rowId > 0) {
            //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
            Uri uri_actividad=null;
            if(uri.toString().contains("p_correcta")){
                uri_actividad = ContentUris.withAppendedId(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.CONTENT_URI, rowId);
            }else if(uri.toString().contains("p_incorrecta")){
                uri_actividad = ContentUris.withAppendedId(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.CONTENT_URI, rowId);
            }else if (uri.toString().contains("p_nn")){
                uri_actividad = ContentUris.withAppendedId(ContratoPalabrasNN.TablaPalabrasNN.CONTENT_URI, rowId);
            }

            getContext().getContentResolver().notifyChange(uri_actividad, null);
            return uri_actividad;
        }
            throw new SQLException("Error al insertar fila en : " + uri);

    }

    //METODO BORRAR
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();// Vuelve a abrir la base de datos para conectar con ella en modo escritura
        int match = convierteUri2Int.match(uri);//Obtengo la uri
        int affected;
        switch (match) {
            case PALABRACORRECTA: //Muchos clientes
                affected=0;
                if(uri.toString().contains("p_correcta")){
                    affected = db.delete(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA,selection,selectionArgs);
                }else if(uri.toString().contains("p_incorrecta")){
                    affected = db.delete(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA,selection,selectionArgs);
                }/*else if (uri.toString().contains("interprete")){
                    affected = db.delete(ContratoInterprete.TablaInterprete.TABLA,selection,selectionArgs);
                }*/
                break;
            case PALABRACORRECTA_ID: //Un sólo cliente
                long idActividad = ContentUris.parseId(uri);
                affected=0;
                if(uri.toString().contains("p_correcta")){
                    affected = db.delete(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID + "= ?" , new String[] {idActividad + ""});
                }else if(uri.toString().contains("p_incorrecta")){
                    affected = db.delete(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA,ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas._ID + "= ?" , new String[] {idActividad + ""});
                }/*else if (uri.toString().contains("interprete")){
                    affected = db.delete(ContratoInterprete.TablaInterprete.TABLA,ContratoInterprete.TablaInterprete._ID + "= ?" , new String[] {idActividad + ""});
                }*/
                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +
                        uri);
        }
        // Notificar cambio asociado a la urigetContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;//Devuelve el numero de filas borradas
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int affected;
        switch (convierteUri2Int.match(uri)) {
            case PALABRACORRECTA:
                affected=0;
                if(uri.toString().contains("p_correcta")){
                    affected = db.update(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, values, selection, selectionArgs);
                }else if(uri.toString().contains("p_incorrecta")){
                    affected = db.update(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA, values, selection, selectionArgs);
                }/*else if (uri.toString().contains("interprete")){
                    affected = db.update(ContratoInterprete.TablaInterprete.TABLA, values, selection, selectionArgs);
                }*/
                break;
            case PALABRACORRECTA_ID:
                //Distintas formas de obtener el idActividad
                //uri.getLastPathSegment()
                //ContentUris.parseId(uri)
                //uri.getPathSegments().get(l)
                String idActividad = uri.getPathSegments().get(1);
                affected=0;
                if(uri.toString().contains("p_correcta")){
                    affected = db.update(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, values, ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID + "= ?" , new String[] {idActividad});
                }else if(uri.toString().contains("p_incorrecta")){
                    affected = db.update(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA, values,ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas._ID + "= ?" , new String[] {idActividad});
                }/*else if (uri.toString().contains("interprete")){
                    affected = db.update(ContratoInterprete.TablaInterprete.TABLA, values,ContratoInterprete.TablaInterprete._ID + "= ?" , new String[] {idActividad});
                }*/
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Obtener base de datos
        SQLiteDatabase db = abd.getReadableDatabase();
        // Comparar Uri
        int match = convierteUri2Int.match(uri);

        Cursor c;

        switch (match) {
            case PALABRACORRECTA:
                // Consultando todos los registros
                c=null;
                if(uri.toString().contains("p_correcta")){
                    c = db.query(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                }else if(uri.toString().contains("p_incorrecta")){
                    c = db.query(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                }else if (uri.toString().contains("p_nn")){
                    c = db.query(ContratoPalabrasNN.TablaPalabrasNN.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                }
                Log.v("Camino", "nos hemos metido por el camino del case PALABRA_ID");
                break;
            case PALABRACORRECTA_ID:
                // Consultando un solo registro basado en el Id del Uri
                c=null;
                long idActividad = ContentUris.parseId(uri);
                if(uri.toString().contains("p_correcta")){
                    c = db.query(ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA, projection, ContratoPalabrasCorrectas.TablaPalabrasCorrectas._ID + "= ? " , new String[] {idActividad + ""},null, null, sortOrder);
                }else if(uri.toString().contains("p_incorrecta")){
                    c = db.query(ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA, projection, ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas._ID + "= ? " , new String[] {idActividad + ""},null, null, sortOrder);
                }else if (uri.toString().contains("p_nn")){
                    c = db.query(ContratoPalabrasNN.TablaPalabrasNN.TABLA, projection, ContratoPalabrasNN.TablaPalabrasNN._ID + "= ? " , new String[] {idActividad + ""},null, null, sortOrder);
                }
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }

        if(uri.toString().contains("p_correcta")){
            c.setNotificationUri(getContext().getContentResolver(), ContratoPalabrasCorrectas.TablaPalabrasCorrectas.CONTENT_URI);
        }else if(uri.toString().contains("p_incorrecta")){
            c.setNotificationUri(getContext().getContentResolver(),ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.CONTENT_URI);
        }else if (uri.toString().contains("p_nn")){
            c.setNotificationUri(getContext().getContentResolver(),ContratoPalabrasNN.TablaPalabrasNN.CONTENT_URI);
        }

        return c;
    }

}

