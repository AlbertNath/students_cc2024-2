package kas.concurrente;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Implementación de un candado (lock) utilizando el algoritmo Test-and-Set (TAS).
 * Este candado está diseñado para manejar situaciones de contención en acceso concurrente
 * utilizando una variable atómica para indicar el estado de bloqueo.
 * 
 * @author PaoPatrol
 * @version 1.0
 */
public class TASLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while(state.getAndSet(true));
    }

    @Override
    public void unlock() {
        state.set(false);
    }
    
}
