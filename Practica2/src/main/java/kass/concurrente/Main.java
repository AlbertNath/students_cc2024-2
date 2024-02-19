package kass.concurrente;

import java.util.ArrayList;
import java.util.List;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;
import kass.concurrente.crypto.Decifrador;

/**
 * Clase Principal
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // String llave = "-4032809067-100-102-128102639-39-8176360";
        Decifrador d = new Decifrador(Constante.LLAVE);
        List<Thread> decifradores = new ArrayList<>();
        
        Long inicio = System.nanoTime();
        //System.out.println(Cifrar.descifraC(Constante.LLAVE, Constante.CONTRASENNA));
        Long fin = System.nanoTime();
        for (int i = 97; i < 123; i++){
            Thread tmp = new Thread(d, ""+((char)i));
            tmp.start();
            decifradores.add(tmp);
        }
        for (Thread t : decifradores)
            t.join();

        Long total = fin-inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
        System.out.println("Practica 2");
    }

    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }
}