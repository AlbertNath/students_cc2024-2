package kass.concurrente.modelo.cuchillo;

/*
 * <p> Clase que implementa la interfaz Cuchillo de 
 * manera concreta. Este representa el cuchillo 
 * de más gama baja que un chef puede tener. </p>    
 */
public class CuchilloHierro implements Cuchillo {
    private Integer redux;

    public CuchilloHierro() {
        this.redux = 100;
    }

    public int corta() {
        return redux;
    } 
}
