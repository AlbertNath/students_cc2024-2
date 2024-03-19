package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Lock;

/**
 * Clase que implementa el candado usando el Legendario
 * algoritmo de PeterGod.
 * No hay mucho que decir, ya saben que hacer
 * @version 1.0
 * @author Kassandra Mirael
 */
public class PetersonLock implements Lock {

    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;

    @Override
    public void lock() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        int j = 1 - (i % 2);
        flag[i % 2] = true; // I'm interested
        victim = i; // you go first
        while (flag[j] && victim == i) {
            // wait
        }
    }

    @Override
    public void unlock() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        flag[i % 2] = false; // I'm not interested
    }
    
}
