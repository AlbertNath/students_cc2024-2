package kass.concurrente.modelo.extra;

import kass.concurrente.modelo.producto.Platillo;

/**
 * Interfaz que implementa el patrón de diseño 
 * <code>Decorator</code> para añadir extras a 
 * los platillos como toppings o adicionales.
 * @author Albert
 */
public interface Extra {
    /**
     * Método para agregar un extra, según 
     * su costo y el del platillo al que se 
     * le agrega.
     * @param platillo el platillo al que 
     * agregamos extras.
     */
    public Double agrega();
}
