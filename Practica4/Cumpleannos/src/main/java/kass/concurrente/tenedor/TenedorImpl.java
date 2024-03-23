package kass.concurrente.tenedor;

import kass.concurrente.candadosImpl.PetersonLock;

import java.util.logging.Logger;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author <PaoPatrol>
 */
public class TenedorImpl implements Tenedor {

    private volatile Integer vecesTomado;
    private Integer id;
    private static PetersonLock peterson = new PetersonLock(); 
    private static final Logger LOGGER = Logger.getLogger(TenedorImpl.class.getName());


    /**
     * Crear una instancia de TenedorImpl
     * @param id --- Id int del Tenedor
     */
    public TenedorImpl(int id){
        this.id = id;
        this.vecesTomado = 0;
    }

    @Override
    public void tomar() {
        peterson.lock();
        this.vecesTomado++;
        LOGGER.info(String.format("El tenedor %d ha sido soltado.", this.id));
    } 

    @Override
    public void soltar() {
        LOGGER.info(String.format("El tenedor %d ha sido soltado.", this.id));
        peterson.unlock();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getVecesTomado() {
        return this.vecesTomado;
       
    }

    @Override
    public void setId(int id) {
        this.id = id ;
        
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        peterson.lock();
        this.vecesTomado = vecesTomado;
        peterson.unlock();
    }
    
}
