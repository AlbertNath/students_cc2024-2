package kass.concurrente.modelos;

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
    final Logger logger = Logger.getLogger(Habitacion.class.getName());

    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion(){
        this.prendido = (this.rnd.nextInt(2) == 1);
        String info = "Habitación inicia con estado: " + this.prendido;
        logger.info(info);
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

            Vocero v = (Vocero) prisionero;

            /* Solo se podrá iniciar a contar cuando el vocero pase por primera vez y 
            apague el interruptor para asegurarse de que no incie prendido y cuente a 
            un prisionero de más. */
            if (Boolean.FALSE.equals(v.getMarcado())) {

                v.setMarcado(true);
                v.incrementaContador();
                logger.info("Apagando ...");
                setPrendido(false);
                return true;

            } else if(Boolean.TRUE.equals(this.prendido)) {

                    logger.info("Apagando ...");
                    setPrendido(false);
                    v.incrementaContador();
                    if (Contante.PRISIONEROS.equals(v.getContador())) {
                        logger.info(Contante.ROJO + "TODOS PASARON!!!" + Contante.RESET);
                        return false;
                    }
            }

        
        } else if (Boolean.FALSE.equals(prisionero.getMarcado())) {

            logger.info(Contante.AZUL + "Prisionero: " + prisionero.getId() +  Contante.RESET);
            logger.info(Contante.AZUL + "larali larala laralu" + Contante.RESET);
            if (Boolean.FALSE.equals(this.prendido)) {
                logger.info(Contante.AZUL + "larali larala laralu" + Contante.RESET);
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
