package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

public class MCSLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;
    class QNode {
        volatile boolean locked = false;
        QNode next = null;
    }

    public MCSLock() {
        //queue = new AtomicReference<>(null); ??
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
            while (qnode.locked);
        }
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        if (qnode.next == null) {
            if (tail.compareAndSet(qnode, null))
                return;
            while (qnode.next == null){
                Thread.currentThread().yield();
            };
        }
        qnode.next.locked = false;
        qnode.next = null;
    }
    
}
