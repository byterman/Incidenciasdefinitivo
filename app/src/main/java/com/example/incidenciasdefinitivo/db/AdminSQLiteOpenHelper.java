package com.example.incidenciasdefinitivo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.incidenciasdefinitivo.objetos.Incidencias;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creamos la base de datos
        db.execSQL("CREATE TABLE Incidencia" +
                "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT,"+
                "priority TEXT,"+
                "date TEXT,"+
                "description TEXT,"+
                "estado TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("Incidencia",null,null);
        db.close();
    }

    public ArrayList<Incidencias> listIncidencia(){
        String sql = "select * from Incidencia";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Incidencias> lista = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToFirst()){
            do{
                String titulo = c.getString(1);
                String prioritat = c.getString(2);
                String descripcion = c.getString(4);
                int id = Integer.parseInt(c.getString(0));
                lista.add(new Incidencias(titulo, prioritat,descripcion,id));
            }while (c.moveToNext());
        }
        c.close();
        return lista;
    }
}
