package kass.concurrente.modelo.extra;

import kass.concurrente.modelo.producto.Platillo;
import kass.concurrente.modelo.producto.Producto;

public class Crema extends Agregado { 
    public Crema(Extra extra) {
        super(extra);
    }
    
    @Override
    public Double agrega() {
        return extra.agrega() + 2.;
    }
}
