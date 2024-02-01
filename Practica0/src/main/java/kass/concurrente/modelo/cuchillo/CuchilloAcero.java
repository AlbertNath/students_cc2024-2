package kass.concurrente.modelo.cuchillo;

public class CuchilloAcero implements Cuchillo {
    private Integer redux;

    public CuchilloAcero() {
        this.redux = 1000;
    }

    public int corta() {
        return redux;
    }
}
