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
    private volatile int[] level;
    private volatile int[] victim;
    //private volatile int threadsInCritical = 0; // Contador de hilos en la sección crítica
    //private  PetersonLock peterson = new PetersonLock();  
    

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
            //peterson.lock();
            level[me] = i;
            victim[i] = me;
            //peterson.unlock();
            
            //while (hasConflict(me, i)) {};
            while(hasConflict(me) && victim[i]==me){
             
            };
            
        }
        //peterson.lock();
        //threadsInCritical++; // Incrementa el contador de hilos en la sección crítica
        //if (threadsInCritical < maxHilosConcurrentes){
        //    level[me] = 0 ; // Permite que entre otro hilo más 
        //}
        //peterson.unlock();
    }

    @Override
    public void release() {
        int me = Integer.parseInt(Thread.currentThread().getName());
        //synchronizedS (this) {
        //peterson.lock();
        //threadsInCritical--; // Decrementa el contador de hilos en la sección crítica
        //}
        level[me] = 0;
        //peterson.unlock();
    }

    public boolean hasConflict(int me) {
        int count = 0; // Contador de hilos en la sección crítica
        for (int k = 0; k < hilos; k++) {
            if (k != me && level[k] >= level[me]) {
                count++;
                if (count >= maxHilosConcurrentes) {
                    return true; 
                }
            }
        }
        return false;
    }
}



