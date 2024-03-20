package kass.concurrente.tenedor;

import java.util.concurrent.Semaphore;
import kass.concurrente.candadosImpl.PetersonLock;
import java.util.concurrent.locks.Lock;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author <Su equipo>
 */
public class TenedorImpl implements Tenedor {

    private volatile Integer vecesTomado;
    private volatile boolean tomado;
    private Integer ID;
    //private Semaphore semaforo = new Semaphore(1);
    private PetersonLock peterson = new PetersonLock();    

    public TenedorImpl(int id){
        this.ID = id;
        this.vecesTomado = 0;
        this.tomado = false;
    }

    @Override
    public void tomar() {
//        try {
//            semaforo.acquire();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        peterson.lock();
        tomado = true;
        this.vecesTomado++;
        System.out.println("El tenedor " + this.ID + " ha sido tomado.");

    }

    @Override
    public void soltar() {
        //semaforo.release();
        tomado = false;
        peterson.unlock();
        System.out.println("El tenedor " + this.ID + " ha sido soltado.");  
    }

    @Override
    public int getId() {
        return ID;
        
    }

    @Override
    public int getVecesTomado() {
        return this.vecesTomado;
       
    }

    @Override
    public void setId(int id) {
        this.ID = id ;
        
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        this.vecesTomado = vecesTomado;
        
    }
    
}
