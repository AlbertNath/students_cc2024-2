package kass.concurrente.modelo.extra;

/**
 * <p>
 * Clase base para agregar ingredientes 
 * extras a un platillo. Implememnta la 
 * interfaz <code> Extra </code>.
 * </p>
 * @author Alberto N. Medel Piña
 * @version 1.0
 */
public class Agregado implements Extra {
    /** Instancia de ingrediente a envolver */
    protected Extra extra;

    /**
     * Constructor de la clase.
     * @param extra la instancia de ingrediente
     * a envolver.
     */
    public Agregado(Extra extra) {
        this.extra = extra;
    }

    /** 
     * Método que regresa la representación 
     * en cadena del nombre del total de 
     * ingredientes agregados según la instancia 
     * más externa que implementa Extra.
     * @return la representación en cadena de los 
     * ingredientes agregados.
     */
    @Override
    public String getNombre() {
        return this.extra.getNombre();
    }

    /** 
     * Método que calcula el agregado por ingredientes
     * extra. Si la instancia envuelta es nula, se regresa 
     * 0.0, en otro caso se calcula el total agregado.
     * @return el costo total del platillo envuelto.
     */
    @Override
    public Double agrega() {
        if (extra == null)
            return 0.;
        return extra.agrega();
    }
}
