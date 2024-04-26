package mx.unam.ciencias.concurrente.snapshots;

/**
 * Interfaz snapshot
 * @version 1.0
 */
public interface Snapshot<T> {
    public abstract void update(T v);
    public abstract T[] scan();
}
