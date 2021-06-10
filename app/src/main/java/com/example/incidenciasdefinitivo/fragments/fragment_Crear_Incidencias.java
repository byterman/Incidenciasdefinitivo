package com.example.incidenciasdefinitivo.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.incidenciasdefinitivo.R;
import com.example.incidenciasdefinitivo.db.AdminSQLiteOpenHelper;

public class fragment_Crear_Incidencias extends Fragment {


    Spinner spinner;
    public fragment_Crear_Incidencias() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__crear__incidencias, container, false);

        EditText titulo,descripcion;
        Button agregar;

        /// objeto sqlite

        AdminSQLiteOpenHelper admin;

        admin = new AdminSQLiteOpenHelper(getContext(),"Incidencia",null,1);

        /// -..-.-.-.-..-
        titulo = v.findViewById(R.id.nombreIncidencia);
        descripcion = v.findViewById(R.id.desc);
        agregar = v.findViewById(R.id.guardarIncidencia);

        spinner = v.findViewById(R.id.spiner);
        String nivel [] ={"Alta","Media","Baja"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,nivel);
        spinner.setAdapter(adapter);




        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titulo.getText().toString().equals("") && descripcion.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Los campos no pueden estar vacios",Toast.LENGTH_SHORT).show();
                }else{

                    String titulostr= titulo.getText().toString();
                    String descripcionstr= descripcion.getText().toString();
                    String spinnerstr=spinner.getSelectedItem().toString();

                    SQLiteDatabase bd= admin.getWritableDatabase();
                    ContentValues registro = new ContentValues();
                    registro.put("title",titulostr);
                    registro.put("priority",spinnerstr);
                    registro.put("date",System.currentTimeMillis() / 1000);
                    registro.put("description",descripcionstr);
                    registro.put("estado","pendent");
                    bd.insert("Incidencia",null,registro);
                    Toast.makeText(getContext(),"El titulo: "+titulostr+ " con la descripcion: "+descripcionstr+" la prioridad: "+spinnerstr+ " y la fecha: "+System.currentTimeMillis() / 1000,Toast.LENGTH_SHORT).show();
                    titulo.setText("");
                    descripcion.setText("");
                    bd.close();

                }

            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}