

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Edge<T> {
    private Vertex<T> source;
    private Vertex<T> destination;

    public Edge(Vertex<T> source, Vertex<T> destination) {
        this.source = source;
        this.destination = destination;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source.getData() +
                ", destination=" + destination.getData() +
                '}';
    }
}