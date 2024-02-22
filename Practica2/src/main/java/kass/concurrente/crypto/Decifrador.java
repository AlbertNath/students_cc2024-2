package kass.concurrente.crypto;

import kass.concurrente.constants.Constante;

/**
 * Clase que reoresenta una instancia para romper 
 * contrseñas por fuerza bruta. Implementa la 
 * interfaz <code>Runnable</code> dado que queremos
 * utilizarla con hilos.
 * @author PaoPatrol. 
 * @version 1.0
 */
public class Decifrador implements Runnable {
    /** La llave a romper */
    private String key;
    /** El alfabeto del sistema criptográfico */
    private char[] alphabet;
    /** Variable compartida para detener los hilos */
    private volatile Boolean found;

    /**
     * Constructor de la clase.
     * @param key la llave a romper.
     */
    public Decifrador(String key){
        this.key = key;
        this.alphabet = Constante.ALFABETO.toCharArray();
        this.found = false;
    }

    /**
     * Método recursivo privado para romper la llave. 
     * Se realiza fuerza bruta sobre todas las posibles 
     * combinaciones de letras del alfabeto. Fijamos la 
     * primera letra de la potencial llave a probar y 
     * agregamos letras del abecedario, probando con 
     * las posibles combinaciones de manera recursiva.
     * @param password la llave a probar
     * @throws Exception 
     */
    private void crack(String password) throws Exception {

        if(Boolean.TRUE.equals(this.found))
            return;
    
        if(password.length() >= 6 && password.length() <= 13) {
            System.out.println(password);
            if(Cifrar.descifraC(key, password)) {
                synchronized(this){
                    this.found = true;
                    System.out.printf("Hilo: %s encontró la contraseña: %s %n", 
                                       Thread.currentThread().getName(), 
                                       password);
                }
                return;
           }
            
        }

        if(password.length() < 6) {
            for(int i = 0; i < alphabet.length; i++) {
                String newPassword = password + alphabet[i];
                crack(newPassword);
            }
        }   
    }

    @Override
    public void run() {
        try {
            String nombre = Thread.currentThread().getName();
            char[] letrasIni = nombre.toCharArray();
            for(char c : letrasIni) {
                crack(String.valueOf(c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
