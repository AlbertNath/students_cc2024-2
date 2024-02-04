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
    
    /**
     * Método para obtener la representación en 
     * cadena de los agregados al platillo.
     * @return la cadena con la secuancia de 
     * agregados al platillo base.
     */
    public String getNombre() {
        return this.nombreProducto; 
    }

    /**
     * Método para agregar un extra, según 
     * su costo y el del platillo al que se 
     * le agrega.
     * @return el costo agregado.
     */
    public Double getCosto() {
        return this.costo;
    }
}
