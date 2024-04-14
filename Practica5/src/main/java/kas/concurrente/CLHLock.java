package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementación de un candado (lock) utilizando el algoritmo de CLH (Craig, Landin, Hagersten).
 * Este candado está diseñado para manejar situaciones de contención en acceso concurrente
 * utilizando una cola de espera basada en nodos.
 * 
 * @author PaoPaotrol
 * @version 1.0
 */
public class CLHLock implements Lock {
    /**
     * Clase interna que representa un nodo en la cola de espera del candado CLH.
     */
    public class QNode {
        /** Indica si este nodo está bloqueado o no. */
        public volatile boolean locked = false;

        /**
         * Devuelve el estado de bloqueo de este nodo.
         * @return true si el nodo está bloqueado, false en caso contrario.
         */
        public boolean get() { 
            return this.locked;
        }

        /**
         * Establece el estado de bloqueo de este nodo.
         * @param v El valor a establecer para el estado de bloqueo (true o false).
         */
        public void set(boolean v) {
            this.locked = v;
        }
    }

    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    /**
     * Crea una instancia del candado CLH.
     */
    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
    }


    @Override
    public void lock() {
        QNode qnode = myNode.get(); 
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked){Thread.currentThread().yield();}
    }


    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }
}
