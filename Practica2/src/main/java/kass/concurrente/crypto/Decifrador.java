package kass.concurrente.crypto;

import kass.concurrente.constants.Constante;

public class Decifrador implements Runnable {
    private String key;
    private char[] alphabet;
    private volatile Boolean found;

    public Decifrador(String key){
        this.key = key;
        this.alphabet = Constante.ALFABETO.toCharArray();
        this.found = false;
    }

    private synchronized void crack(String password) throws Exception {
    
        if(password.length() >= 7 && password.length() <= 13 && Cifrar.descifraC(key, password)) {
            this.found = true;
            System.out.printf("Hilo: %s encontró la contraseña: %s", 
                              Thread.currentThread().getName(), 
                              password);
            return;
            
        }

        if(password.length() < 13) {
            for(int i = 0; i < alphabet.length; i++) {
                String newPassword = password + alphabet[i];
                crack(newPassword);
            }
        }   
    }

    @Override
    public void run() {
        try {
            String idChar = Thread.currentThread().getName();
            crack(idChar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
