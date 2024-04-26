package mx.unam.ciencias.concurrente.snapshotsImpl;

import java.util.Arrays;

import mx.unam.ciencias.concurrente.snapshots.Memento;
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
        StampedSnap<T>[] oldCopy;
        StampedSnap<T>[] newCopy;
        boolean[] moved = new boolean[aTable.length];
        oldCopy = collect();
        collect: while (true) {
            newCopy = collect();
            for (int i = 0; i < aTable.length; i++) {
                if(oldCopy[i].stamp != newCopy[i].stamp) {
                    if(moved[i])
                        return oldCopy[i].snap;
                    else {
                        moved[i] = true;
                        oldCopy = newCopy;
                        continue collect;
                    }
                }
            }
            T[] result = (T[]) new Object[aTable.length];
            for(int i = 0; i < aTable.length; i++)
                result[i] = newCopy[i].value;

            return result;
        }
    }    

    public Memento<T>[] save() {
        Memento<T>[] mementos = new Memento[aTable.length];
        for (int i = 0; i < aTable.length; i++)
            mementos[i] = new Memento<>(aTable[i].snap);
        return mementos;
    }

    public void restore(Memento<T>[] mementos) {
        for (int i = 0; i < aTable.length; i++) {
            aTable[i].snap = (T[]) Arrays.copyOf(mementos[i].getState(), mementos[i].getState().length);
        }
    }
}