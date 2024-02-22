package kass.concurrente;

import java.util.ArrayList;
import java.util.List;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Decifrador;

/**
 * Clase Principal
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Decifrador d = new Decifrador(Constante.LLAVE);
        List<Thread> decifradores = new ArrayList<>();
        
        Long inicio = System.nanoTime();
        
        String alfabeto = Constante.ALFABETO;
        int nLetras = alfabeto.length() / Constante.HILOS;
        
        String letrasIni;
        
        for (int i = 0; i < Constante.HILOS; i++){
            
            if(alfabeto.length() >= nLetras) {
                letrasIni = alfabeto.substring(0, nLetras);
                alfabeto = alfabeto.substring(nLetras);
            } else {
                letrasIni = alfabeto;
            }
            
            Thread tmp = new Thread(d, letrasIni);
            tmp.start();
            decifradores.add(tmp);
            
        }
        
        for (Thread t : decifradores)
        t.join();
        
        Long fin = System.nanoTime();
        Long total = fin-inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
        System.out.println("Practica 2");
    }

    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }
}