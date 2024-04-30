import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Graph<T> {
    private List<Vertex<T>> vertices;
    private int degree;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.degree = 0;
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public int getDegree() {
        return degree;
    }

    public boolean isGraphConnected() {
       // Sepa que es conexo
        return false;
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex<T> source, Vertex<T> destination) {
        // Mmmmmmmmmm ... lógica para añadir una arista
        source.addAdjacentVertex(destination);
        destination.addAdjacentVertex(source);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph:\n");
        for (Vertex<T> vertex : vertices) {
            sb.append(vertex).append("\n");
        }
        return sb.toString();
    }
}
