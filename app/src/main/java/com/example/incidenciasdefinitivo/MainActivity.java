package com.example.incidenciasdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText user = findViewById(R.id.user);
        final EditText password = findViewById(R.id.password);
        final Button btnlogin =  findViewById(R.id.button_login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrasena = password.getText().toString();
                String usuario = user.getText().toString();
                if(  contrasena.equals("admin") && usuario.equals("admin")){
                    goTo();
                }
            }
        });

    }

    public void goTo(){
        Intent intent = new Intent(this, Menugeneral.class);
        startActivity(intent);
    }
}