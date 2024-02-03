package kass.concurrente.modelo.extra;

public class Queso extends Agregado {
    public Queso(Extra extra) {
        super(extra);
    }
    
    @Override
    public Double agrega() {
        return extra.agrega() + 5.;
    }
}
