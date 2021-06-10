package com.example.incidenciasdefinitivo.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.incidenciasdefinitivo.R;
import com.example.incidenciasdefinitivo.db.AdminSQLiteOpenHelper;


public class fragment_Info_App extends Fragment {

    Button botonborrar,Configuracion;
    AdminSQLiteOpenHelper admin;
    fragment_configuracion fragmentConfiguracion = new fragment_configuracion();


    public fragment_Info_App() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__info__app, container, false);

        Configuracion = v.findViewById(R.id.button4);

        Configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragmentConfiguracion);
            }
        });


        admin = new AdminSQLiteOpenHelper(getContext(),"Incidencia",null,1);

        // Inflate the layout for this fragment
        botonborrar = v.findViewById(R.id.botonborrar);

        botonborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mostrardialogo();
            }
        });


        return v;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }


    public void mostrardialogo(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Cuidado!");
        builder.setMessage("Quieres eliminar todas las incidencias?")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        admin.delete();

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