package mx.unam.ciencias.concurrente.snapshotsImpl;

import java.util.Arrays;

import mx.unam.ciencias.concurrente.snapshots.Snapshot;
import mx.unam.ciencias.concurrente.stamped.StampedSnap;

public class WFSnapshot<T> implements Snapshot<T> {
    private StampedSnap<T>[] aTable;

    public WFSnapshot(int capacity, T init) {
        aTable = (StampedSnap<T>[]) new StampedSnap[capacity];
        for (int i = 0; i < aTable.length; i++)
            aTable[i] = new StampedSnap<>(init);
    }

    private StampedSnap<T>[] collect() {
        return Arrays.copyOf(aTable, aTable.length);
    }

    @Override
    public void update(T v) {
        Integer id = Integer.valueOf(Thread.currentThread().getName());
        T[] snap = scan();
        StampedSnap<T> oldValue = aTable[id];
        StampedSnap<T> newValue = 
            new StampedSnap<>(oldValue.stamp+1, v, snap);
        aTable[id] = newValue;
    }

    @Override
    public T[] scan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scan'");
    }
    
}
