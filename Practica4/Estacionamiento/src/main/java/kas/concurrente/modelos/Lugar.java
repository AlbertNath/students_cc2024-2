package kas.concurrente.modelos;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

import kas.concurrente.constante.Contante;

/**
 * Clase que modela un Lugar
 * El lugar consta de un id
 * un booleano que nos dice si esta dispoible
 * y un objeto del tipo Semaphore (El semaforo)
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Lugar {
    private Integer id;
    private boolean disponible;
    private Semaphore semaforo;
    private Integer vecesEstacionado;
    final Logger logger = Logger.getLogger(Lugar.class.getName());


    /**
     * Metodo constructor
     * El lugar por defecto esta disponible
     * Pueden llegar un numero n de carros en el peor de los casos
     * veces estacionado sera el numero de veces que se han estacianado en el lugar
     * Si llegan 2 carros y ambos se estacionan, entonces, su valor sera de 2
     * @param id El id del Lugar
     */
    public Lugar(int id){
        this.id = id;
        this.disponible = true;
        vecesEstacionado = 0;
        semaforo = new Semaphore(1);
    }

    /**
     * En este metodo se simula que se estaciona
     * PELIGRO: ESTAS ENTRANDO A LA 2da SECCION CRITICA
     * Cambia el valor de disponible a false
     * Y se simula que vas pastel de cumple :D
     * Al final, imprime un texto color ROJO diciendo que va salir (Esperen instrucciones para esto)
     * @throws InterruptedException Si algo falla
     */
    public void estaciona() throws InterruptedException{
        semaforo.acquire();
        disponible = false;
        vecesEstacionado ++;
        semaforo.release();
        vePorPastel();
        logger.info(Contante.ROJO + "¡Saliendo del lugar " + id);
    }

    /**
     * En este metodo se genera la sumulación de espera
     * Se genera un tiempo entre 1 y 5 segundos
     * Es pseudo aleatorio
     * @throws InterruptedException En caso de que falle
     */
    public void vePorPastel() throws InterruptedException{
        int tiempoEspera = (int) (Math.random() * 5) + 1;
        Thread.sleep(tiempoEspera * 1000);
    }

    /**
    * Obtiene el atributo id
     * @return --- int atributo dd
     */
    public int getId (){
        return id;
    } 
    /**
     * Obtiene el atributo disponible
     * @return --- boolean atributo disponible
     */
    public boolean getDisponible() {
        return disponible;
    }
    /**
     * Obtiene el atributo vecesEstacionado
     * @return --- Integer atributo veces estacionado
     */
    public Integer getVecesEstacionado(){
        return vecesEstacionado;
    }

    
}
