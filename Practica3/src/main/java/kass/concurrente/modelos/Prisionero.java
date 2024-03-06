package kass.concurrente.modelos;

/**
 * Clase que modela un prisioner
 * @version 1.0
 * @author <PaoPatrol>
 */
public class Prisionero {
    /** Identificador del prisionero */
    protected Integer id;
    /** Bandera para identificar si es vocero */
    protected Boolean esVocero;
    /** Bandera para indicar si ha pasado */
    protected Boolean marcado;

    /**
     * Metodo constructor para generar un prisionero
     * @param id El identificador del prisionero
     * @param esVocero true si es Vocero false en otro caso
     * @param marcado true si ya paso
     */
    public Prisionero(Integer id, Boolean esVocero, Boolean marcado){
        this.id = id;
        this.esVocero = esVocero;
        this.marcado = marcado;
    }

    /**
     * Método que regresa el identificador del prisionero.
     * @return el identificador del prisionero.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que regresa si el prisionero es vocero.
     * @return true si es vocero, false en otro caso.
     */
    public Boolean getEsVocero() {
        return esVocero;
    }

    /**
     * Método que regresa si el prisionero ha pasado por
     * la sala.
     * @return true si ya ha pasado y cambiado el estado
     * del switch. false en otro caso.
     */
    public Boolean getMarcado() {
        return marcado;
    }

    public Integer getContador() {
        return -1; //!!!!
    }

    /**
     * Método que establece el estaod del 
     * prisionero.
     * @param estado el estaod al que ponerle 
     * al prisionero.
     */
    public void setMarcado(Boolean estado) {
        this.marcado = estado;
    }

    public void incrementaContador() {
        
    }
}
