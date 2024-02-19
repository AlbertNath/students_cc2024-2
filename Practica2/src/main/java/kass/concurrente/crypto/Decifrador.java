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

    private void crack(String password) throws Exception {

        if(this.found)
            return;
    
        if(password.length() >= 6 && password.length() <= 6) {
            System.out.println(password);
            if(Cifrar.descifraC(key, password)) {
                synchronized(this){
                    this.found = true;
                    System.out.printf("Hilo: %s encontró la contraseña: %s \n", 
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
            String idChar = Thread.currentThread().getName();
            // crack(idChar + "yaabmywat");
            crack(idChar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
