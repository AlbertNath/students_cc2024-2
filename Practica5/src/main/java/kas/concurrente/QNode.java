package kas.concurrente;

public class QNode {
    private volatile boolean locked;

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
