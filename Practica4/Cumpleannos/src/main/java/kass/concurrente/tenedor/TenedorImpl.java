package kass.concurrente.tenedor;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author <Su equipo>
 */
public class TenedorImpl implements Tenedor {

    private Integer tomado;
    private Integer ID;

    public TenedorImpl(int id){
        this.ID = id;
        this.tomado = 0;

    }

    @Override
    public void tomar() {
        this.tomado++;
        System.out.println("El tenedor " + this.ID + " ha sido tomado.");
    }

    @Override
    public void soltar() {
        System.out.println("El tenedor " + this.ID + " ha sido soltado.");
    }

    @Override
    public int getId() {
        return ID;
        
    }

    @Override
    public int getVecesTomado() {
        return this.tomado;
       
    }

    @Override
    public void setId(int id) {
        this.ID = id ;
        
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        this.tomado = vecesTomado;
        
    }
    
}
