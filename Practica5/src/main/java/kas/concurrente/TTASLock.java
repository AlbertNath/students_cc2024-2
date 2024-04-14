package kas.concurrente;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Implementación de un candado (lock) utilizando el algoritmo Test-and-Test-and-Set (TTAS).
 * Este candado está diseñado para manejar situaciones de contención en acceso concurrente
 * utilizando una variable atómica para indicar el estado de bloqueo.
 * 
 * @author PaoPatrol
 * @version 1.0
 */
public class TTASLock implements Lock{
    AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while (true) {
            while (state.get());
            if (!state.getAndSet(true))
                return;
        }
    }

    @Override
    public void unlock() {
        this.state.set(false);
    }
    
}
