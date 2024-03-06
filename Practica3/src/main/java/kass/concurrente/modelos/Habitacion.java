package kass.concurrente.modelos;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

import kass.concurrente.constantes.Contante;
/**
 * Clase que fungira como habitacion
 * Se sabe que existe un interruptor que nos dice
 * si el foco esta prendido o no
 * Se desconoce el estado inicial del foco (Usar un random, para que sea
 * pseudoaleatorio el estado inicial)
 * @author <PaoPatrol>
 * @version 1.0
 */
public class Habitacion {
    /** Variable booleana que representa el estado
     * del switch
     */
    private Boolean prendido;
    /** Generador de números pseudoaleatorios */
    private Random rnd = new Random();
    /** Logger */
    final Logger LOG = Logger.getLogger(Habitacion.class.getName());
    private Contante constante;

    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion(){
        this.prendido = (this.rnd.nextInt(2) == 1);
        LOG.info("Habitación inicia con estado: " + this.prendido);
    }

    /**
     * Metodo que permite al prisionero entrar a la habitacion
     * Recordemos que solo uno pasa a la vez, esta es la SECCION CRITICA
     * En este caso se controla desde fuera
     * Es similar al algoritmo que progonan y similar al de su tarea
     * El prisionero espera una cantidad finita de tiempo
     * @param prisionero El prisionero que viene entrando
     * @return false si ya pasaron todos, true en otro caso
     * @throws InterruptedException Si falla algun hilo
     */
    public Boolean entraHabitacion(Prisionero prisionero) throws InterruptedException{
        Thread.sleep(this.rnd.nextLong(Contante.CINCO_SEGUNDOS));
        if (Boolean.TRUE.equals(prisionero.getEsVocero())) {
            prisionero = (Vocero) prisionero;
            if(Boolean.TRUE.equals(this.prendido)) {
                System.out.println("Apagando");
                setPrendido(false);
                prisionero.incrementaContador();
                //System.out.println(prisionero.getContador() );
                if (prisionero.getContador() == Contante.PRISIONEROS - 1) {
                    LOG.info(Contante.ROJO + "TODOS PASARON!!!" + Contante.RESET);
                    return false;
                }
            }
        } else {
            if (Boolean.TRUE.equals(prisionero.getMarcado()))
                return true; 

            LOG.info(Contante.AZUL + "Prisionero: " + prisionero.getId() +  Contante.RESET);
            LOG.info(Contante.AZUL + "larali larala laralu" + Contante.RESET);
            //System.out.println("Prendiendo (Hilo " + prisionero.getId() +")");
            if (Boolean.FALSE.equals(this.prendido)) {
                LOG.info(Contante.AZUL + "larali larala laralu" + Contante.RESET);
                setPrendido(true);
                prisionero.setMarcado(true);
            }
        }   
        return true; 
    }

    /**
     * Método que establece el interruptor a un 
     * estado booleano determinado.
     * @param estado el estado al que poner el 
     * interruptor,
     */
    public void setPrendido(Boolean estado) {
        this.prendido = estado;
    }

    /**
     * Métido que regresa el estado del switch.
     * @return el estado del switch.
     */
    public Boolean getPrendido() {
        return this.prendido;
    }
}
