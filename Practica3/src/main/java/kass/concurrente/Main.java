package kass.concurrente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import kass.concurrente.constantes.Contante;
import kass.concurrente.modelos.Habitacion;
import kass.concurrente.modelos.Prisionero;
import kass.concurrente.modelos.Vocero;

import static kass.concurrente.constantes.Contante.LOGS;

/**
 * Clase principal, se ejecuta todo la simulacion
 * Como en el cuento.
 * @author <Equipo>
 * @version 1.0
 */
public class Main implements Runnable {

    Lock lock; 
    public volatile List<Prisionero> prisioneros;    
    private volatile Boolean stop;
    private Habitacion h;
    public Main(){
        lock = new ReentrantLock();
        this.prisioneros = new ArrayList<>();
        this.h = new Habitacion();
        this.stop = true;
        //Agregar lo que haga falta para que funcione
    }

    /*
     * INSTRUCCIONES:
     * 1.- Ya genere el lock, es un reentrantLock, investiguen que hace
     * 2.- Tenenemos que tener un lugar el donde se albergaran los prisioneros
     * 3.- Tenemos que tener un lugar donde se albergan los Hilos
     * 4.- Se nececita un objeto de tipo Habitacion para que sea visitada
     * 5.- Aqui controlaremos el acceso a la habitacion, aunque por defecto tenia exclusion mutua
     * aqui hay que especificar el como se controlara
     * 6.- Hay que estar ITERANDO constantemente para que todos los prisiones puedan ir ingresando
     */
    @Override
    public void run() {
        while(Boolean.TRUE.equals(this.stop)){
            Integer id = Integer.valueOf(Thread.currentThread().getName());
            System.out.println("Hilo entrante: " + id);
            try {
                if (Boolean.TRUE.equals(this.stop)) {
                this.lock.lock();
                this.stop = h.entraHabitacion(prisioneros.get(id));
                this.lock.unlock();        
            }
            System.out.printf("Estado del hilo %d (%b): %s\n", id, prisioneros.get(id).getEsVocero() , this.stop);
            System.out.println("Hilo saliente: " + id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
        }
        
    }


    public static void main(String[] args) {
        Main m = new Main();

        List<Thread> threads = new ArrayList<>(); 

        for(int i = 0; i < Contante.PRISIONEROS; i++){ 
            if (i == 0) {
                Prisionero vocero = new Vocero(i, true, false);
                m.prisioneros.add(vocero);
            } else {
                Prisionero prisionero = new Prisionero(i, false, false);
                m.prisioneros.add(prisionero);
            }

            Thread t = new Thread(m,""+i);
            threads.add(t); 
            t.start(); 
        }

        for(Thread th : threads){
            try {
                th.join();
            } catch (InterruptedException e) {e.printStackTrace();}
        }

        final Logger LOG = Logger.getLogger(Main.class.getName()); // EJEMPLO LOGGER

        //if(LOGS) LOG.info("HOLA SOY UN MENSAJE");
    }
}