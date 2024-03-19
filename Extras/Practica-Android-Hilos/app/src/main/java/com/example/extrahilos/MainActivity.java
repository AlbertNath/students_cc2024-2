package com.example.extrahilos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.extrahilos.Hilos.Matrices;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    EditText num_hilos;
    EditText edit_matriz;
    Button botonMat;
    Button botonCont;
    TextView resultado;
    //Matrices matriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String ubicacion = "";
        InputStream is1000 = this.getResources().openRawResource(R.raw.mat1000);//Cambiar nombres por el de otras matrices

        //num_hilos = (EditText) findViewById(R.id.eje);
        //edit_matriz = (EditText) findViewById(R.id.edit_matriz);
        botonMat = (Button) findViewById(R.id.ejecutarMatMult);
        botonCont = (Button) findViewById(R.id.ejecutarContador);

        botonMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultMatriz.class);
                startActivity(intent);
            }
        });

        botonCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Contadoractivity.class);
                startActivity(intent);
            }
        });
    }
}