package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;
public class CLHLock implements Lock {
    public class QNode {
        public volatile boolean locked;

        public QNode() {
            this.locked = false;
        }

        public boolean get() { 
            return this.locked;
        }

        public void set(boolean v) {
            this.locked = v;
        }
    }

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
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked);
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }
    
}
