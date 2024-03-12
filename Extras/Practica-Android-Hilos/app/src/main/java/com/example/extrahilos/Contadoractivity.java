package com.example.extrahilos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.extrahilos.Hilos.Contador;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Contadoractivity extends AppCompatActivity {
    Button ejecutar;
    EditText numHilos;
    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contadoractivity);
        ejecutar = (Button) findViewById(R.id.ejecutar);
        numHilos = (EditText) findViewById(R.id.edit_num_hilos);
        resultado = (TextView) findViewById(R.id.resultado);

        ejecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contador c = new Contador();
                String hilosS = "" + numHilos.getText();
                int hilosTotales = Integer.parseInt(hilosS);
                System.out.println(hilosTotales);
                List<Thread> hilos = new ArrayList<>();
                for(int i = 0; i < hilosTotales; i++){
                    Thread tmp = new Thread(c, String.valueOf(i));
                    tmp.start();
                    hilos.add(tmp);
                }
                for (Thread t : hilos) {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String res = String.valueOf(c.getContador());
                resultado.setText(res);

            }
        });
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});
    }
}