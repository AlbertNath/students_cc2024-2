package mx.unam.ciencias.concurrente.stamped;

public class StampedSnap<T> {
    public long stamp;
    public T    value;
    public T[]  snap;

    public StampedSnap(T value) {
        this.stamp = 0;
        this.value = value;
        this.snap  = null;
    }

    public StampedSnap(long label, T value, T[] snap) {
        this.stamp = label; // EL LIBRO DICE OTRA COSA???
        this.value = value;
        this.snap  = snap;
    }
}
