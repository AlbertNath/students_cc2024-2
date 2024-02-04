package kass.concurrente.modelo.extra;

/**
 * <p> Clase que representa un ingrediente 
 * adicional a un platillo 
 * </p>
 * @author Alberto N. Medel Piña
 * @version 1.0
 */
public class IngredienteExtra extends Agregado {
    /** El nombre del platillo */
    private String nombre;
    /** El costo extra del ingrediente */
    private Double costo;

    /**
     * Constructor de la clase. Envuelve una instancia
     * que implemente la interfaz <code>Extra</code> 
     * siguiendo el patrón <code>Decorator<code> e 
     * inicializa nombre y costo agregados.
     * @param nombre el nombre del ingrediente extra.
     * @param costo el costo del ingrediente extra.
     * @param extra la instancia a envolver.
     */
    public IngredienteExtra(String nombre, Double costo, Extra extra) {
        super(extra);
        this.nombre = nombre;
        this.costo = costo;
    }

    /**
     * Método para obtener la representación en 
     * cadena de los agregados al platillo.
     * @return la cadena con la secuancia de 
     * agregados al platillo base.
     */
    @Override
    public String getNombre() {
        return this.nombre + " -> " + this.extra.getNombre();
    }
    
    /**
     * Método para agregar un extra, según 
     * su costo y el del platillo al que se 
     * agrega.
     * @return el costo agregado.
     */
    @Override
    public Double agrega() {
        return extra.agrega() + this.costo;
    }
}
