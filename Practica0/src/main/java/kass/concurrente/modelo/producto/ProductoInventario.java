package kass.concurrente.modelo.producto;

/**
 * Clase que modela un producto de un inventario
 * @author Kassandra Mirael
 */
public class ProductoInventario extends Producto{
    private Integer unidades; 
    private Integer retraso;

    public ProductoInventario(String nombre, Double costo) {
        super(nombre, costo); 
        this.unidades = 0;
    }

    public ProductoInventario(String nombre, Double costo, Integer initUnidades) {
        super(nombre, costo); 
        this.unidades = initUnidades;
    }
}
