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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[][] procesaMat(InputStream is, int size) {
        BufferedReader raw = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        List<String> lines = new ArrayList<>();
        try {
            for(String l; (l = raw.readLine()) != null; lines.add(l));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //int[][] resMat = new int[lines.size()][lines.get(0).split(" ").length];
        int[][] resMat = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] tmp = lines.get(i).split(" ");
            for(int j = 0; j < size; j++) {
                resMat[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        return resMat;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[][] leer(String mat) throws  IOException {
        //List<String> mats = Arrays.asList("10", "100", "1000");
        //if (!mats.contains(mat))
        //    throw new IOException("Matriz no existente");
        int size = 0;
        switch (mat) {
            case "10":
                size = R.raw.mat10;
                break;
            case "100":
                size = R.raw.mat100;
                break;
            case "1000":
                size = R.raw.mat1000;
                break;
            default:
                throw new IOException("Matriz no existente");
        }

        InputStream is = this.getResources().openRawResource(size);//actualicen esto, para poner cualquier archvio
        return procesaMat(is, Integer.parseInt(mat));
    }
}