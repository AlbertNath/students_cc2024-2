package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;
import kas.concurrente.QNode;

public class CLHLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<>();
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
        qnode.set(true);
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.get());
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.set(false);
        myNode.set(myPred.get());
    }
    
}
