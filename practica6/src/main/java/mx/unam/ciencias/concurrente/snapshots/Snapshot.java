package mx.unam.ciencias.concurrente.snapshots;

/**
 * Interfaz snapshot
 * @version 1.0
 */
public interface Snapshot<T> {
    public void update(T v);
    public T[] scan();
}
