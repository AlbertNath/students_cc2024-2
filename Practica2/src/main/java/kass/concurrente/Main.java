package kass.concurrente;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;


/**
 * Por el momento basado en :
 * https://www.geeksforgeeks.org/print-all-combinations-of-given-length/
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Long inicio = System.nanoTime();
        String palabraCifrada = Constante.LLAVE;
        String alfabeto = Constante.ALFABETO;


        encontrarContra(palabraCifrada, alfabeto, 9);

        Long fin = System.nanoTime();
        Long total = fin - inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
        System.out.println("Practica 2");
    }

    private static boolean encontrarContra(String palabraCifrada, String alfabeto, int longitud) {
        char[] set = alfabeto.toCharArray();
        return probarContra(set, palabraCifrada, "", set.length, longitud);
    }

    private static boolean probarContra(char[] set, String palabraCifrada, String prefix, int n, int k) {
        if (k == 0) {
            try {
                if (Cifrar.descifraC(palabraCifrada, prefix)) {
                    System.out.println("Contraseña correcta encontrada: " + prefix);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            if (probarContra(set, palabraCifrada, newPrefix, n, k - 1)) {
                return true;
            }
        }
        return false;
    }

    public static double nanoSegundoASegundo(Long tiempo) {
        return tiempo * 1.0 * Math.pow(10, -9);
    }
}
/*
 * _  _  _  _
 * A 27 27 27
 */