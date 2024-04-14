package kas.concurrente;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Implementación de un candado (lock) utilizando el algoritmo de backoff.
 * Este candado está diseñado para manejar situaciones de contención en acceso concurrente
 * mediante un mecanismo de espera exponencial.
 * 
 * @author PaoPaotrol
 * @version 1.0
 */
public class BackoffLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY = 1;
    private static final int MAX_DELAY = 1000;

    @Override
    public void lock() {
        Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
        while (true) {
            while (state.get());
            if (!state.getAndSet(true)){
                return;
            } else {
                try {
                    backoff.backoff();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unlock() {
        this.state.set(false);
    }
    
}
