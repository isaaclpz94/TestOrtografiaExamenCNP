package com.example.administrador.testortografiaexamencnp.ayudante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasCorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasIncorrectas;
import com.example.administrador.testortografiaexamencnp.contrato.ContratoPalabrasNN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="ortografia_cnp.sqlite";
    public static final int DATABASE_VERSION = 1;
    private Context ctx;

    public Ayudante(Context context) {
        super(context,  context.getFilesDir() +"/"+ DATABASE_NAME, null,DATABASE_VERSION);
        this.ctx = context;
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
                ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA+" text, "+
                ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRAINCORRECTA_ID+ " integer)";
        db.execSQL(sql);

        String sql2;
        sql2="create table "+ ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA+ " ("+
                ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas._ID+ " integer primary key autoincrement, "+
                ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.PALABRA+" text) ";

        db.execSQL(sql2);


        try {
            ArrayList<String> palabras = readFromAssets(ctx, "palabras.txt");

            String sql4 = "INSERT INTO " + ContratoPalabrasCorrectas.TablaPalabrasCorrectas.TABLA + " ("+ ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRA +","+ContratoPalabrasCorrectas.TablaPalabrasCorrectas.PALABRAINCORRECTA_ID+") VALUES (?, ?)";
            db.beginTransaction();

            SQLiteStatement stmt = db.compileStatement(sql4);
            int contador = 0;
            for (String palabra: palabras) {
                if(palabra != null && !palabra.equals("")) {
                    stmt.bindString(1, palabra);
                    stmt.bindLong(2, contador);
                    stmt.execute();
                    stmt.clearBindings();

                    contador++;
                }
            }
            Log.v("ESTADO", "VAMOS A INSERTAR " + palabras.size() + " PALABRAS");

            db.setTransactionSuccessful();
            db.endTransaction();

            String sql5 = "INSERT INTO " + ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.TABLA + " ("+ ContratoPalabrasIncorrectas.TablaPalabrasIncorrectas.PALABRA +") VALUES (?)";
            db.beginTransaction();

            SQLiteStatement sqlstmt = db.compileStatement(sql5);
            ArrayList<String> palabrasIncorrectas = getPalabrasIncorrectas(palabras);

            for (String palabra: palabrasIncorrectas) {
                if(palabra != null && !palabra.equals("")) {
                    sqlstmt.bindString(1, palabra);
                    sqlstmt.execute();
                    sqlstmt.clearBindings();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*************************** MÉTODOS PRIVADOS **********************************/

    //Crear palabras incorrectas
    private ArrayList<String> getPalabrasIncorrectas(ArrayList<String> palabras){

        ArrayList<String> palabrasIncorrectas = new ArrayList<>();

        for(String palabra: palabras){
            if(palabra.contains("b")){
                palabrasIncorrectas.add(palabra.replaceFirst("b","v"));
            }else if(palabra.contains("y")){
                palabrasIncorrectas.add(palabra.replaceFirst("y","ll"));
            }else if(palabra.contains("ll")){
                palabrasIncorrectas.add(palabra.replaceFirst("ll","y"));
            }else if(palabra.contains("v")){
                palabrasIncorrectas.add(palabra.replaceFirst("v","b"));
            }else{
                if(palabra.contains("ns")){
                    palabrasIncorrectas.add(palabra.replaceFirst("ns","s"));
                }else if(palabra.contains("á")){
                    palabrasIncorrectas.add(palabra.replace("á", "a"));
                }else if(palabra.contains("é")){
                    palabrasIncorrectas.add(palabra.replace("é", "e"));
                }else if(palabra.contains("í")){
                    palabrasIncorrectas.add(palabra.replace("í", "i"));
                }else if(palabra.contains("ó")){
                    palabrasIncorrectas.add(palabra.replace("ó", "o"));
                }else if(palabra.contains("ú")){
                    palabrasIncorrectas.add(palabra.replace("ú", "u"));
                }else{
                    palabrasIncorrectas.add(palabra.replaceFirst("a","á"));
                }
            }
        }

        return palabrasIncorrectas;
    }

    //Leer fichero palabras
    private ArrayList<String> readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        ArrayList<String> palabras = new ArrayList<>();
        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        int contador = 0; //Para que coja las palabras de 20 en 20
        while (mLine != null) {
            contador++;
            sb.append(mLine); // process line
            mLine = reader.readLine();
            if(mLine != null && !mLine.contains("~")) {
                if ((contador % 20 == 0) && (mLine.contains("b") || mLine.contains("v") || mLine.contains("ll") || mLine.contains("y"))) { //Que coja una palabra de cada 20 que cumpla las condiciones
                    if(mLine.contains("\\")){
                        palabras.add(acentuar(mLine));
                    }else if(mLine.contains("~")){
                        palabras.add(solucionarÑ(mLine));
                    }else{
                        palabras.add(mLine);
                    }
                }else if(contador % 50 == 0){ //Para que coja una palabra de cada 50 que no se salga de la ocndición anterior
                    if(mLine.contains("\\")){
                        palabras.add(acentuar(mLine));
                    }else if(mLine.contains("~")){
                        palabras.add(solucionarÑ(mLine));
                    }else{
                        palabras.add(mLine);
                    }
                }
            }
        }
        reader.close();
        return palabras;
    }

    //Solucionar palabras codificadas con ñ
    private String solucionarÑ(String palabra){

        int index = palabra.indexOf("~"); //Guardamos la posicion del caracter ~
        palabra = palabra.replace("~", ""); //Lo borramos

        palabra = palabra.substring(0,index-1) + "ñ" + palabra.substring(index-1, palabra.length()); // Metemos la nueva letra en la cadena
        return palabra;
    }

    //Solucionar palabras acentuadas con código
    private String acentuar(String palabra){
        int index = palabra.indexOf("\\");
        String letra = palabra.substring(index-1, index); // Guardamos la letra a acentuar
        palabra = palabra.replace("\\", ""); // Quitamos la barra

        String nuevaletra = "ERROR";
        switch (letra){ // ¿Qué letra es?
            case "a":
                nuevaletra = "á";
                break;
            case "e":
                nuevaletra = "é";
                break;
            case "i":
                nuevaletra = "í";
                break;
            case "o":
                nuevaletra = "ó";
                break;
            case "u":
                nuevaletra = "ú";
                break;
        }

        StringBuilder sb = new StringBuilder(palabra);
        sb.deleteCharAt(index-1); // Borramos la vocal a acentuar
        palabra = sb.toString();

        palabra = palabra.substring(0,index-1) + nuevaletra + palabra.substring(index-1, palabra.length()); // Metemos la nueva letra en la cadena

        return palabra;
    }
}