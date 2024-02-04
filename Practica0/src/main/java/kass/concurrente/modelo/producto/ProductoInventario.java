package kass.concurrente.modelo.producto;

/**
 * Clase que modela un producto de un inventario
 * @author Kassandra Mirael
 */
public class ProductoInventario extends Producto{
    /** Las unidades disponibles */
    private Integer unidades; 
    /** Penalización en caso de no tener existencias */
    private Integer retraso = 2000;

    /**
     * Constructor de la clase.
     * @param nombre el nombre del producto. 
     * @param costo el costo del producto.
     */
    public ProductoInventario(String nombre, Double costo) {
        super(nombre, costo); 
        this.unidades = 0;
    }

    /**
     * Constructor de la clase.
     * @param nombre el nombre del producto. 
     * @param costo el costo del producto.
     * @param initUnidades unidades iniciales.
     */
    public ProductoInventario(String nombre, Double costo, Integer initUnidades) {
        super(nombre, costo); 
        this.unidades = initUnidades;
    }

    /**
     * Método que devuelve si el producto actual posee
     * existencias o no. 
     * @return true si al menos existe una unidad del producto 
     * o false en otro caso.
     */
    private boolean conExistencias() {
        return this.unidades > 0;
    }

    /**
     * Método que simula el utilizar un producto, decrementando en 
     * uno la unidad si se cuenta con existencias. En caso contrario
     * se penaliza retrasando la ejecución para simular el tener que 
     * reabastecer las existencias. 
     */
    public void consumir() {
        if (!conExistencias())
            try {
                System.out.println("Sin existencias de " + this.nombreProducto 
                                   + "\nEspere...");
                Thread.sleep(retraso);
                this.unidades += 2;
            } catch (InterruptedException e) {
                System.err.println("Error al traer ingrediente");
                System.exit(1);
            }
        this.unidades--;
    }
}
