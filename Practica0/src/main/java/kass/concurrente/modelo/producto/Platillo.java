package kass.concurrente.modelo.producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela un platillo
 * @author Kassandra Mirael
 */
public class Platillo {
    private List<Producto> agregados;
    private Double costo;
    private Integer tiempoPrep;
    private String nombre; 

    public Platillo() {
        this.agregados  = new ArrayList<>();
        this.costo      = 0.;
        this.tiempoPrep = 0;
        this.nombre     = "Placeholder";
    }

    public Platillo(String nombre, Integer tiempoPrep) {
        this.agregados  = new ArrayList<>();
        this.costo      = 0.;
        this.tiempoPrep = tiempoPrep;
        this.nombre     = nombre;
    }

    public Platillo(List<Producto> productos, Double costo, 
                    Integer tiempoPrep, String nombre) {
        this.agregados  = productos;
        this.costo      = costo;
        this.tiempoPrep = tiempoPrep;
        this.nombre     = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getTiempoCoccion() {
        return this.tiempoPrep;
    }

    public List<Producto> getProductosRequeridos() {
        return this.getProductosRequeridos();
    }

    /**
     * Este metodo calcula el precio sobre el precio de los items, se ignora
     * el precio base.
     * @return El precio de la suma de cada item utilizado
     */
    public Double calculaPrecio(){
        if (this.agregados.isEmpty())
            return costo;

        Double total = 0.;
        for (Producto p : agregados)
            total += p.getCosto();
        
        return total;
    }
    
}
