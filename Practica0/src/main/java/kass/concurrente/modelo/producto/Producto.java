package kass.concurrente.modelo.producto;

/**
 * Clase que modela un producto
 * @author Kassandra Mirael
 */
public class Producto {
    /** El nombre del producto */
    protected String nombreProducto;
    /** El costo del producto */
    protected Double costo;

    /**
     * Constructor de la clase <code> Producto </code>.
     * Inicializa con un nombre por defecto y un costo 
     * nulo ya que no se proporciona información sobre 
     * dicho producto.
     */
    public Producto() {
        this.nombreProducto = "Placeholder";
        this.costo          = 0.;
    }
    
    /**
     * Constructor de la clase <code> Producto </code>.
     * Inicializa con un nombre y un costo que se pasan
     * como argumentos 
     * @param nombre el nombre del producto.
     * @param costo el costo del producto.
     */
    public Producto(String nombre, Double costo) {
        this.nombreProducto = nombre;
        this.costo          = costo;
    }
    
    public String getNombre() {
        return this.nombreProducto; 
    }

    public Double getCosto() {
        return this.costo;
    }
}
