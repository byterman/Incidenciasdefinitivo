package com.example.incidenciasdefinitivo.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incidenciasdefinitivo.Menugeneral;
import com.example.incidenciasdefinitivo.R;
import com.example.incidenciasdefinitivo.db.AdminSQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class fragment_incidencia_al_detalle extends Fragment {

    AdminSQLiteOpenHelper admin;

    public fragment_incidencia_al_detalle() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incidencia_al_detalle, container, false);

        TextView titulo,prioridad,descripcion,fecha,estado;
        Button borrarr;

        borrarr = v.findViewById(R.id.button3);

        titulo= v.findViewById(R.id.insert_titulo2);
        prioridad = v.findViewById(R.id.insert_prioridad2);
        descripcion = v.findViewById(R.id.insert_descripcion2);
        estado = v.findViewById(R.id.Insert_estado);
        fecha = v.findViewById(R.id.insert_fecha);


        admin = new AdminSQLiteOpenHelper(getContext(),"Incidencia",null,1);

        SQLiteDatabase bd=admin.getReadableDatabase();
        int ids= fragment_Mostrar_Incidencias.posicion;
        String idstr = Integer.toString(ids);
        Log.e("soyunlog",idstr);
        Cursor fila=bd.rawQuery("SELECT title,priority,description,date,estado FROM Incidencia WHERE id="+idstr,null);

                if (fila.moveToFirst()){
                    Log.e("soyunlog",fila.getString(0));

                    titulo.setText(fila.getString(0));
                    prioridad.setText(fila.getString(1));
                    descripcion.setText(fila.getString(2));

                    String sDate1=fila.getString(3);
                    int fechas = Integer.parseInt(sDate1);
                    Date actual_hora=new java.util.Date(fechas*1000);
                    String hora=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(actual_hora);

                    fecha.setText(hora.toString());
                    estado.setText(fila.getString(4));


                    bd.close();
                }else{
                    Log.e("soyunlog","nadade nada");
                }

        borrarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrardialogo();
            }
        });


        return v;
    }

    public void mostrardialogo(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Cuidado!");
        builder.setMessage("Quieres eliminar la incidencia?")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //admin.delete();

                        Toast.makeText(getContext(),"Incidencias eliminadas",Toast.LENGTH_SHORT).show();



                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Cancelado",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}