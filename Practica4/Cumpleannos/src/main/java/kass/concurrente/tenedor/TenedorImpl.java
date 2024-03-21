package kass.concurrente.tenedor;

import kass.concurrente.candadosImpl.PetersonLock;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author <PaoPatrol>
 */
public class TenedorImpl implements Tenedor {

    private volatile Integer vecesTomado;
    private Integer ID;
    private PetersonLock peterson = new PetersonLock();  
    
    
    /**
     * Crear una instancia de TenedorImpl
     * @param id --- Id int del Tenedor
     */
    public TenedorImpl(int id){
        this.ID = id;
        this.vecesTomado = 0;
    }

    @Override
    public void tomar() {
        peterson.lock();
        this.vecesTomado++;
        System.out.println("El tenedor " + this.ID + " ha sido tomado.");

    }

    @Override
    public void soltar() {
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
        peterson.lock();
        this.vecesTomado = vecesTomado;
        peterson.unlock();
    }
    
}
