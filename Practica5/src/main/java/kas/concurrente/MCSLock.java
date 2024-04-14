package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementación de un candado (lock) utilizando el algoritmo MCS (Mellor-Crummey, Scott).
 * Este candado está diseñado para manejar situaciones de contención en acceso concurrente
 * utilizando una cola de espera basada en nodos.
 * 
 * @author PaoPaotrol
 * @version 1.0
 */
public class MCSLock implements Lock {
    /** Referencia atómica al último nodo de la cola de espera. */
    AtomicReference<QNode> tail;
    /** Variable local de cada hilo que apunta al nodo correspondiente en la cola de espera. */
    ThreadLocal<QNode> myNode;

    /**
     * Clase interna que representa un nodo en la cola de espera del candado MCS.
     */
    class QNode {
        /** Indica si este nodo está bloqueado o no. */
        volatile boolean locked = false;
        /** Referencia al siguiente nodo en la cola de espera. */
        QNode next = null;
    }

    /**
     * Crea una instancia del candado MCS.
     */
    public MCSLock() {
        tail = new AtomicReference<>(null);
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }
    
    @Override
    public void lock() {
        QNode qnode = myNode.get();
        QNode pred = tail.getAndSet(qnode);
        if (pred != null) {
            qnode.locked = true;
            pred.next = qnode;
            while (qnode.locked) Thread.yield();
        }
    }


    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        if (qnode.next == null) {
            if (tail.compareAndSet(qnode, null))
                return;
            while (qnode.next == null) Thread.yield();
        }
        qnode.next.locked = false;
        qnode.next = null;
    }
}
