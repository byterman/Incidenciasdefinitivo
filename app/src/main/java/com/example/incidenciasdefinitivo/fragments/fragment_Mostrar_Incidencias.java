package com.example.incidenciasdefinitivo.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incidenciasdefinitivo.Menugeneral;
import com.example.incidenciasdefinitivo.R;
import com.example.incidenciasdefinitivo.db.AdminSQLiteOpenHelper;
import com.example.incidenciasdefinitivo.objetos.Incidencias;

import java.util.ArrayList;


public class fragment_Mostrar_Incidencias extends Fragment {

    int color1 = Color.parseColor("#2f3136");
    int color2 = Color.parseColor("#464647");
    int numero= 1;
    public static int posicion;
    fragment_incidencia_al_detalle fragmentIncidenciaAlDetalle = new fragment_incidencia_al_detalle();

    AdminSQLiteOpenHelper admin;

    ArrayList<Incidencias> lista;

    String [] nombres = {"lMvej8Xl9ho",
            "lMvej8Xl9ho",
            "lMvej8Xl9ho",
            "lMvej8Xl9ho",
            "lMvej8Xl9ho",
            "lMvej8Xl9ho"};
    int tamano;
    RecyclerView favR;

    public fragment_Mostrar_Incidencias() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__mostrar__incidencias, container, false);

        //Menugeneral.id.add();
        admin = new AdminSQLiteOpenHelper(getContext(),"Incidencia",null,1);
        SQLiteDatabase bd= admin.getReadableDatabase();
        Cursor fila=bd.rawQuery("SELECT count(id) FROM Incidencia",null);

        if (fila.moveToFirst()){
            Log.e("soyunlog",fila.getString(0));
            String tamanostr = fila.getString(0);
            tamano = Integer.parseInt(tamanostr);
            bd.close();
        }else{
            Log.e("soyunlog","nadade nada");
        }

        admin.listIncidencia();

        // Inflate the layout for this fragment
        favR = v.findViewById(R.id.rv_mostrardatos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        favR.setLayoutManager(linearLayoutManager);
        AdaptadorPeliculasFavoritas adapter = new AdaptadorPeliculasFavoritas(this, admin.listIncidencia());
        favR.setAdapter(adapter);


        return v;
    }

    private class AdaptadorPeliculasFavoritas extends RecyclerView.Adapter<AdaptadorPeliculasFavoritas.AdaptadorPeliculasFavoritasHolder> {
        private ArrayList<Incidencias> arrayList;
        private fragment_Mostrar_Incidencias context;

        public AdaptadorPeliculasFavoritas(fragment_Mostrar_Incidencias mos,ArrayList<Incidencias> array){
            arrayList = array;
            context = mos;
        }

        @NonNull
        @Override
        public AdaptadorPeliculasFavoritasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorPeliculasFavoritasHolder(getLayoutInflater().inflate(R.layout.item_incidencias,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorPeliculasFavoritasHolder holder, int position) {

            holder.imprimit(position);

            holder.layoutid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();

                    posicion = arrayList.get(position).getId();
                    loadFragment(fragmentIncidenciaAlDetalle);

                }
            });


        }


        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class AdaptadorPeliculasFavoritasHolder extends RecyclerView.ViewHolder {

            TextView tituloinsert,descripcioninsert,prioridadinsert;
            View layoutid;
            ImageView prioridadrepresentada;
            public AdaptadorPeliculasFavoritasHolder(@NonNull View itemView) {
                super(itemView);

                //iv1=itemView.findViewById(R.id.imageView);

                descripcioninsert=itemView.findViewById(R.id.insert_descripcion);
                tituloinsert=itemView.findViewById(R.id.insert_titulo);
                prioridadinsert=itemView.findViewById(R.id.insert_prioridad);
                prioridadrepresentada = itemView.findViewById(R.id.imagen_representa_prioridad);
                layoutid = itemView.findViewById(R.id.item_general);





            }


            public void imprimit(int position) {


                if (numero == 1){
                    layoutid.setBackgroundColor(color1);
                    numero=2;
                }else{
                    layoutid.setBackgroundColor(color2);
                    numero=1;
                }


                tituloinsert.setText(arrayList.get(position).getContenido());
                prioridadinsert.setText(arrayList.get(position).getPrioridad());


                if (arrayList.get(position).getPrioridad().equals("Alta")){
                    prioridadrepresentada.setImageResource(R.drawable.rojo);
                }else if(arrayList.get(position).getPrioridad().equals("Media")){
                    prioridadrepresentada.setImageResource(R.drawable.amarillo);
                }else{
                    prioridadrepresentada.setImageResource(R.drawable.verde);
                }
                descripcioninsert.setText(arrayList.get(position).getDesc());

                /*
                SQLiteDatabase bd=admin.getReadableDatabase();
                int ids= lista.get(position).getId();
                String idstr = Integer.toString(ids);
                Cursor fila=bd.rawQuery("SELECT title,priority,description,id FROM Incidencia WHERE id="+idstr,null);

                if (fila.moveToFirst()){
                    Log.e("soyunlog",fila.getString(0));

                    tituloinsert.setText(fila.getString(0));
                    prioridadinsert.setText(fila.getString(1));
                    descripcioninsert.setText(fila.getString(2));

                    Menugeneral.id.add(Integer.parseInt(fila.getString(3)));
                    bd.close();
                }else{
                    Log.e("soyunlog","nadade nada");
                }
                */






            }
        }

    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }


}