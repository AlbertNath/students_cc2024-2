package kass.concurrente.modelo.producto;

import java.util.ArrayList;
import java.util.List;

import kass.concurrente.modelo.extra.Extra;

/**
 * Clase que modela un platillo
 * @author Kassandra Mirael
 */
public class Platillo implements Extra {
    /** 
     * La lista de productos que se requieren 
     * para la elaboración
    */
    private List<Producto> requeridos;
    /** El costo del platillo base */
    private Double costo;
    /** El tiempo estimado de preparación */
    private Integer tiempoPrep;
    /** El nombre del platillo */
    private String nombre; 

    /**
     * Constructor de la clase sin argumentos. 
     * Inicializa el platillo con valores por 
     * defecto (peligroso en un inventario más
     * realista).
     */
    public Platillo() {
        this.requeridos  = new ArrayList<>();
        this.costo      = 0.;
        this.tiempoPrep = 0;
        this.nombre     = "Placeholder";
    }

    /**
     * Constructor de la clase. Inicializa solo 
     * el nombre y tiempo de preparación.
     * @param nombre el nombre del platillo.
     * @param tiempoPrep el tiempo de preparación
     * del platillo.
     */
    public Platillo(String nombre, Integer tiempoPrep) {
        this.requeridos  = new ArrayList<>();
        this.costo      = 0.;
        this.tiempoPrep = tiempoPrep;
        this.nombre     = nombre;
    }

    /**
     * Constructor de la clase. Inicializa 
     * la lista de productos requeridos, su costo, el  
     * tiempo de preparación y el nombre.
     * @param productos la lista de productos 
     * necesarios para el platillo.
     * @param costo el costo del platillo. 
     * @param tiempoPrep el tiempo de preparación
     * del platillo.
     * @param nombre el nombre del platillo.
     */
    public Platillo(List<Producto> productos, Double costo, 
                    Integer tiempoPrep, String nombre) {
        this.requeridos  = productos;
        this.costo      = costo;
        this.tiempoPrep = tiempoPrep;
        this.nombre     = nombre;
    }

    /**
     * Método para obtener la representación en 
     * cadena del platillo.
     * @return la cadena con el nombre del platillo.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Método para devolver el tiempo de cocción.
     * @return el tiempo de cocción.
     */
    public Integer getTiempoCoccion() {
        return this.tiempoPrep;
    }

    /**
     * Método para devolver la lista de productos 
     * requeridos para elaborar un platillo.
     * @return la lista de productos requeridos.
     */
    public List<Producto> getProductosRequeridos() {
        return this.requeridos;
    }

    /**
     * Este metodo calcula el precio sobre el precio de los items, se ignora
     * el precio base.
     * @return El precio de la suma de cada item utilizado
     */
    public Double calculaPrecio(){
        if (this.requeridos.isEmpty())
            return costo;

        Double total = 0.;
        for (Producto p : requeridos)
            total += p.getCosto();
        
        return total;
    }

    @Override
    public Double agrega() {
        return this.calculaPrecio();
    }
}
