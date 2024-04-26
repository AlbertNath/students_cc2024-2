package mx.unam.ciencias.concurrente.snapshots;

import java.util.ArrayList;
import java.util.List;

public class Caretaker<T> {

    //private Snapshot originator;
    private List<Memento<T>> history;
    private List<Memento<T>> localhistory;

    public Caretaker() {
        history = new ArrayList<>();
        localhistory = new ArrayList<>();
    }

    public void add(Memento<T> memento) {
        localhistory.add(memento);
    }

    public void commit() {
        history.addAll(localhistory);
        localhistory.clear();
    }

    public Memento<T> undo() {
        if(!history.isEmpty()) {
            int i = history.size() - 1;
            return history.remove(i);
        }
        return null;
    }

    
}
