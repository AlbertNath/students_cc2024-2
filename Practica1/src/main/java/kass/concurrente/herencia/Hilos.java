package kass.concurrente.herencia;

/**
 * En esta clase, debes crear un contador extendiendo de la clase Thread
 * @author Kassandra Mirael
 * @version 1.1
 */
public class Hilos extends Thread {
    public static final Integer totalHilos = 3;
    public static final Integer TOTAL = 10000;
    private static Integer contador = 0;

    @Override
    public void run() {
        String s = Thread.currentThread().getName();
        Integer id = Integer.parseInt(s.substring(s.length() - 1));
        Integer sleepTime = (id == 0)? 1000 : 1;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < TOTAL; i++)
            contador++;   
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] hilos = new Thread[totalHilos];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Hilos();
            hilos[i].start();
        }

        for (Thread i : hilos) 
            i.join();

        System.out.printf("Contador total: %d", contador);
    }
}
