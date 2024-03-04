package kass.concurrente.modelos;

import java.util.Objects;
import java.util.Random;
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

    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion(){
        this.prendido = (this.rnd.nextInt(2) == 1);
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
            Prisionero v = (Vocero) prisionero;
            if(Boolean.TRUE.equals(this.prendido)) {
                setPrendido(false);
                v.incrementaContador();
                return (v.getContador() == Contante.PRISIONEROS - 1);
            }
        } else {
           if (Boolean.TRUE.equals(prisionero.getMarcado()))
                return true; 

           if (Boolean.TRUE.equals(this.prendido)) {
                this.prendido = false;
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
