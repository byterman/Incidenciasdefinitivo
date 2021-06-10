package com.example.incidenciasdefinitivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.incidenciasdefinitivo.fragments.fragment_Crear_Incidencias;
import com.example.incidenciasdefinitivo.fragments.fragment_Info_App;
import com.example.incidenciasdefinitivo.fragments.fragment_Mostrar_Incidencias;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Menugeneral extends AppCompatActivity {

    fragment_Crear_Incidencias fragmentcrearincidencias = new fragment_Crear_Incidencias();
    fragment_Info_App fragmentinfoapp = new fragment_Info_App();
    fragment_Mostrar_Incidencias fragmentMostrarIncidencias = new fragment_Mostrar_Incidencias();

    BottomNavigationView bottomNavigationView;
    public static ArrayList<Integer> id = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menugeneral);


        bottomNavigationView = findViewById(R.id.menu_navegacion);






        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.fragment_buscar:
                        loadFragment(fragmentcrearincidencias);
                        return true;
                    case R.id.fragment_menu:
                        loadFragment(fragmentinfoapp);
                        return true;
                    case R.id.fragment_tienda:
                        loadFragment(fragmentMostrarIncidencias);
                        return true;
                }
                return false;
            }
        });
    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }
}