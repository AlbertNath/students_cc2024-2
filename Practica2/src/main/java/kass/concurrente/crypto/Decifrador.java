package kass.concurrente.crypto;

import java.util.Random;

import kass.concurrente.constants.Constante;

public class Decifrador implements Runnable {
    private String key;
    private Random rand;
    private Integer pswrdLen;
    private Integer alphabetLen;
    private volatile Boolean found;

    public Decifrador(String key, Integer pswrdLen){
        this.key = key;
        this.found = false;
        this.pswrdLen = pswrdLen;
        this.alphabetLen = Constante.ALFABETO.length();
        this.rand = new Random();
    }

    private synchronized void crack() throws Exception {
        char[] alphabet = Constante.ALFABETO.toCharArray();
        String idChar = Thread.currentThread().getName();
        
        
        while (!found) {
            StringBuilder password = new StringBuilder(idChar);
            for(int i = 1; i < pswrdLen; i++){
                Integer curr = rand.nextInt(27 - 1) ;
                password.append(alphabet[curr]);
                System.out.println(curr);
            }
            if(Cifrar.descifraC(key, password.toString())){
                this.found = true;
                System.out.printf("Hilo: %s encontró la contraseña: %s", idChar, password);
            }
        }
    }

    @Override
    public void run() {
        try {
            crack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
