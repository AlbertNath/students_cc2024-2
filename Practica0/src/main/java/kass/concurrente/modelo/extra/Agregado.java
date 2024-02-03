package kass.concurrente.modelo.extra;

import kass.concurrente.modelo.producto.Platillo;

public class Agregado implements Extra {
    protected Extra extra;

    public Agregado(Extra ex) {
        this.extra = ex;
    }

    @Override
    public Double agrega() {
        if (extra == null)
            return 0.;
        return extra.agrega();
    }
}
