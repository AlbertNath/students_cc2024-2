package kass.concurrente.modelo.cuchillo;

/*
 * <p> Clase que implementa la interfaz Cuchillo de 
 * manera concreta. Este representa el cuchillo 
 * de gama más alta que un chef puede tener. </p>    
 */
public class CuchilloJapones implements Cuchillo {
    private Integer redux;

    public CuchilloJapones() {
        this.redux = 1500;
    }

    public int corta() {
        return redux;
    }
}
