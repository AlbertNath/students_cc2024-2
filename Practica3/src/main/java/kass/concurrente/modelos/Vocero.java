package kass.concurrente.modelos;

/**
 * Este ess quien lleva la cuenta de los prisioneros que han entrado a la habitacion
 * a parte de los atributos de Prisionero, tambien posee un contador
 * @author <PaoPatrol>
 * @version 1.0
 */
public class Vocero extends Prisionero{
    /** Contador del vocero */
    protected Integer contador;

    /**
     * Constructor de la clase.
     * @param id el identificador del vocero.
     * @param esVocerotrue si es Vocero false en otro caso
     * @param marcado true si ya paso
     */
    public Vocero(Integer id, Boolean esVocero, Boolean marcado) {
        super(id, esVocero, marcado);
        this.contador = 0;
        //Completar y hacer documentacion
    }
   
    /**
     * Obtiene el contador 
     * @return contador
     */
    public Integer getContador() {
        return this.contador;
    }

    /***
     * Aumenta el contador
     */
    public void incrementaContador() {
        this.contador++;
    }
}
