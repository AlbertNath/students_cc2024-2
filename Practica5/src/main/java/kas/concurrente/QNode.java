package kas.concurrente;

/**
 * Clase que representa un nodo en una cola de espera.
 * Este nodo se utiliza en diferentes algoritmos de bloqueo para mantener el estado de bloqueo
 * de un hilo específico.
 * 
 * @author [Tu nombre]
 * @version 1.0
 */
public class QNode {
    /** Indica si este nodo está bloqueado o no. */
    private volatile boolean locked;

    /**
     * Crea una instancia de QNode con el estado de bloqueo inicializado como desbloqueado (false).
     */
    public QNode() {
        this.locked = false;
    }

    /**
     * Obtiene el estado de bloqueo de este nodo.
     * @return true si el nodo está bloqueado, false si está desbloqueado.
     */
    public boolean get() { 
        return this.locked;
    }

    /**
     * Establece el estado de bloqueo de este nodo.
     * @param v true para bloquear el nodo, false para desbloquearlo.
     */
    public void set(boolean v) {
        this.locked = v;
    }
}
