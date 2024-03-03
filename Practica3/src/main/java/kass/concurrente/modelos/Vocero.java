package kass.concurrente.modelos;

/**
 * Este ess quien lleva la cuenta de los prisioneros que han entrado a la habitacion
 * a parte de los atributos de Prisionero, tambien posee un contador
 * @author <Su equipo>
 * @version 1.0
 */
public class Vocero extends Prisionero{
    protected Integer contador;
    public Vocero(Integer id, Boolean esVocero, Boolean marcado) {
        super(id, esVocero, marcado);
        this.contador = 0;
        //Completar y hacer documentacion
    }
   
    @Override
    public Integer getContador() {
        return this.contador;
    }

    @Override
    public void incrementaContador() {
        this.contador++;
    }
    //Mismo caso que el otro, annadir getter and setter... No hereda?
}
