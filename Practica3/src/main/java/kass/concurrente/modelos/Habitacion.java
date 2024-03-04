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
    private Boolean prendido;
    private Random rnd = new Random();
    final Logger LOG = Logger.getLogger(Habitacion.class.getName());

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
                System.out.println(prisionero.getContador() );
                if (prisionero.getContador() == Contante.PRISIONEROS - 1) {
                    LOG.info("TODOS PASARON!!!");
                    return false;
                }
            }
        } else {
            if (Boolean.TRUE.equals(prisionero.getMarcado()))
                return true; 

            System.out.println("Prendiendo (Hilo " + prisionero.getId() +")");
            if (Boolean.FALSE.equals(this.prendido)) {
                setPrendido(true);
                prisionero.setMarcado(true);
            }
        }   
        return true; 
//
//        if (Boolean.TRUE.equals(prisionero.getMarcado()))
//            return true;
//
//        if (Boolean.TRUE.equals(prisionero.getEsVocero()) && (Boolean.FALSE.equals(this.prendido))) {
//            prisionero.incrementaContador();
//            this.prendido = true;  
//                
//            if (Objects.equals(prisionero.getContador(), Contante.PRISIONEROS - 1))
//                return false;    
//            
//            return true;
//        }
//
//        if (Boolean.TRUE.equals(this.prendido)) {
//            this.prendido = false;
//            prisionero.setMarcado(true);
//        }
//        return true;
    }

    public void setPrendido(Boolean estado) {
        this.prendido = estado;
    }

    public Boolean getPrendido() {
        return this.prendido;
    }
}
