package mx.unam.ciencias.concurrente.stamped;

public class StampedValue<T> {
    public long stamp;
    public T value;

    // Valor inicial con marca de tiempo cero
    public StampedValue(T init) {
        this.stamp = 0;
        this.value = init;
    }

    // Valores posteriores con marca de tiempo proporcionada
    public StampedValue(long stamp, T value) {
        this.stamp = stamp;
        this.value = value;
    }

    public static StampedValue<?> max(StampedValue<?> x, StampedValue<?> y) {
        if (x.stamp > y.stamp) {
            return x;
        } else {
            return y;
        }
    }

    public static final StampedValue<?> MIN_VALUE = new StampedValue<>(null);
}