package kas.concurrente;

import java.util.Random;

/**
 * Implementación de un mecanismo de retroceso (backoff) para controlar la espera exponencial.
 * Esta clase se utiliza en conjunción con algoritmos de bloqueo para ayudar a manejar la contención
 * y evitar la contienda en situaciones de acceso concurrente.
 *
 * @version 1.0
 * @author PaoPaotrol
 */
public class Backoff {
    final int minDelay;
    final int maxDelay;
    final Random random;
    int limit;

    /**
     * Crea una instancia del mecanismo de retroceso (backoff) con los valores mínimos y máximos especificados.
     *
     * @param min El tiempo mínimo de espera en milisegundos.
     * @param max El tiempo máximo de espera en milisegundos.
     */
    public Backoff(int min, int max) {
        this.minDelay = min;
        this.maxDelay = max;
        this.limit    = minDelay;
        this.random   = new Random();
    }

    /**
     * Realiza una operación de retroceso, que implica una espera aleatoria entre el tiempo mínimo y máximo
     * especificados en la creación del objeto Backoff.
     *
     * @throws InterruptedException Si la espera es interrumpida mientras el hilo está dormido.
     */
    public void backoff() throws InterruptedException {
        int delay = random.nextInt(limit);
        limit = Math.min(maxDelay, 2 * limit);
        Thread.sleep(delay);
    }
}
