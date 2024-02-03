package kass.concurrente.modelo.producto;

/**
 * Clase que modela un producto de un inventario
 * @author Kassandra Mirael
 */
public class ProductoInventario extends Producto{
    private Integer unidades; 
    private Integer retraso = 2000;

    public ProductoInventario(String nombre, Double costo) {
        super(nombre, costo); 
        this.unidades = 0;
    }

    public ProductoInventario(String nombre, Double costo, Integer initUnidades) {
        super(nombre, costo); 
        this.unidades = initUnidades;
    }

    public Integer getExistencias() {
        return this.unidades;
    }

    public void consumir() {
        if (this.unidades <= 0)
            try {
                System.out.println("Sin existencias de " + this.nombreProducto 
                                   + "\nEspere...");
                Thread.sleep(retraso);
                this.unidades += 2;
            } catch (InterruptedException e) {
                System.out.println("Error al traer ingrediente");
                System.exit(1);
            }
        this.unidades--;
    }
}
