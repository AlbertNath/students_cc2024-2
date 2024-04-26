package mx.unam.ciencias.concurrente.snapshotsImpl;

import java.util.Arrays;

import mx.unam.ciencias.concurrente.snapshots.Memento;
import mx.unam.ciencias.concurrente.snapshots.Snapshot;

/**
 * Implementación de SequentialSnapshot.
 * @version 1.0
 */
public class SequentialSnapshot<T> implements Snapshot<T> {

    T[] aValue;

    public SequentialSnapshot(int capacity, T init) {
        aValue = (T[]) new Object[capacity];
        for (int i = 0 ; i < aValue.length; i++)
            aValue[i] = init;
    }

    @Override
    public synchronized void update(T v) {
        Integer id = Integer.valueOf(Thread.currentThread().getName());
        aValue[id] = v;
    }

    @Override
    public synchronized T[] scan() {
        return Arrays.copyOf(aValue, aValue.length);
    }

    public Memento<T> save() {
        return new Memento<>(scan());
    }

    public void restore(Memento<T> memento) {
        this.aValue = memento.getState();
    }
}
