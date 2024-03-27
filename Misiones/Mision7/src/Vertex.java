import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Vertex<T> {
    private T data;
    private int visitCount;
    private List<Vertex<T>> adjacentVertices;
    private List<Edge<T>> edges;
    private int degree;
    private final Lock lock;

    public Vertex(T data) {
        this.data = data;
        this.visitCount = 0;
        this.adjacentVertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.degree = 0;
        this.lock = new ReentrantLock();
    }

    public T getData() {
        return data;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void incrementVisitCount() {
        visitCount++;
    }

    public List<Vertex<T>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex<T> vertex) {
        adjacentVertices.add(vertex);
        degree++;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void addEdge(Edge<T> edge) {
        edges.add(edge);
    }

    public int getDegree() {
        return degree;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                ", visitCount=" + visitCount +
                ", degree=" + degree +
                '}';
    }
}
