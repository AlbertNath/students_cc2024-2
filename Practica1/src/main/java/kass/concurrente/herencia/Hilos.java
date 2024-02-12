package kass.concurrente.herencia;

/**
 * En esta clase, debes crear un contador extendiendo de la clase Thread
 * @author Kassandra Mirael
 * @version 1.1
 */
public class Hilos extends Thread {
    public static final Integer HILOS = 3;
    public static final Integer TOTAL = 10000;
    public static Integer contador = 0;

    @Override
    public void run() {
        for(int i = 0; i < TOTAL; i++)
            contador++;   
    }
    public static void main(String[] args) throws InterruptedException {
        
    }
}
