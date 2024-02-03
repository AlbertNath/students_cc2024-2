package kass.concurrente.modelo.producto;

import java.util.ArrayList;
import java.util.List;

import kass.concurrente.modelo.extra.Extra;

/**
 * Clase que modela un platillo
 * @author Kassandra Mirael
 */
public class Platillo implements Extra {
    private List<Producto> requeridos;
    private Double costo;
    private Integer tiempoPrep;
    private String nombre; 

    public Platillo() {
        this.requeridos  = new ArrayList<>();
        this.costo      = 0.;
        this.tiempoPrep = 0;
        this.nombre     = "Placeholder";
    }

    public Platillo(String nombre, Integer tiempoPrep) {
        this.requeridos  = new ArrayList<>();
        this.costo      = 0.;
        this.tiempoPrep = tiempoPrep;
        this.nombre     = nombre;
    }

    public Platillo(List<Producto> productos, Double costo, 
                    Integer tiempoPrep, String nombre) {
        this.requeridos  = productos;
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
