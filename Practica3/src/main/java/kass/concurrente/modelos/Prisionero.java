package kass.concurrente.modelos;

/**
 * Clase que modela un prisioner
 * @version 1.0
 * @author <PaoPatrol>
 */
public class Prisionero {
    protected Integer id;
    protected Boolean esVocero;
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

    public Integer getId() {
        return id;
    }

    public Boolean getEsVocero() {
        return esVocero;
    }

    public Boolean getMarcado() {
        return marcado;
    }

    public Integer getContador() {
        return -1; //!!!!
    }

    public void setMarcado(Boolean estado) {
        this.marcado = estado;
    }

    public void incrementaContador() {
        
    }
}
