package kass.concurrente.modelo.persona;

import kass.concurrente.modelo.cuchillo.Cuchillo;

public class Chef extends Persona{ 
    private Cuchillo cu;
   
    public Chef(String nombre, Integer edad, Cuchillo cu) {
        super(nombre, edad);
        this.cu = cu;
    }
}
