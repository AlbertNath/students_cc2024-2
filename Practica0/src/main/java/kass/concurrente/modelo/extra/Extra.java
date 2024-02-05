package kass.concurrente.modelo.extra;
/**
 * <p>
 * Interfaz que implementa el patrón de diseño 
 * <code>Decorator</code> para añadir extras a 
 * los platillos como toppings o adicionales.
 * </p>
 * @author Alberto N. Medel Piña
 * @version 1.0
 */
public interface Extra {

    /**
     * Método para obtener la representación en 
     * cadena de los agregados al platillo.
     * @return la cadena con la secuancia de 
     * agregados al platillo base.
     */
    public String getNombre();

    /**
     * Método para agregar un extra, según 
     * su costo y el del platillo al que se 
     * le agrega.
     * @return el costo agregado.
     */
    public Double agrega();
}
