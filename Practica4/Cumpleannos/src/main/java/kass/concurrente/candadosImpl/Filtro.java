package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Semaphore;

/**
 * Clase que modela el Algoritmo del Filtro Modificado
 * Este algoritmo es similar al del filtro, lo diferente es que
 * permite una cantidad m de hilos SIMULTANEOS en la seccion critica
 * Todo es casi igual, solo realiza la modificacion pertinente para esto
 * @version 1.0
 * @author Kassandra Mirael
 */
public class Filtro implements Semaphore {

    private final int hilos;
    private final int maxHilosConcurrentes;
    private int[] level;
    private int[] victim;
    private volatile int threadsInCritical = 0; // Contador de hilos en la sección crítica
    

    public Filtro(int hilos, int maxHilosConcurrentes) {
        this.hilos = hilos;
        this.maxHilosConcurrentes = maxHilosConcurrentes;
        level = new int[hilos];
        victim = new int[hilos]; 
        for (int i = 0; i < hilos; i++) {
            level[i] = 0;
        }
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return maxHilosConcurrentes;
    }
    
    @Override
    public void acquire() {
        int me = Integer.parseInt(Thread.currentThread().getName());
        for (int i = 1; i < hilos; i++) { // Intenta adquirir el bloqueo en múltiples niveles simultáneamente
            level[me] = i;
            victim[i] = me;
            // Spin mientras existan conflictos
            while (hasConflict(me, i)) {};
        }
        threadsInCritical++; // Incrementa el contador de hilos en la sección crítica
        if (threadsInCritical < maxHilosConcurrentes){
            level[me] = 0 ; // Permite que entre otro hilo más 
        }
    }

    @Override
    public void release() {
        int me = Integer.parseInt(Thread.currentThread().getName());
        //synchronizedS (this) {
        threadsInCritical--; // Decrementa el contador de hilos en la sección crítica
        //}
        level[me] = 0;
    }

    private boolean hasConflict(int me, int myLevel) {
        for (int k = 0; k < level.length; k++) {
            if (k != me && level[k] >= myLevel && victim[myLevel] == me) {
                return true; // Existe un conflicto
            }
        }
        return false; // No hay conflictos
    }
}



