package kass.concurrente.modelo.persona;

import java.util.List;

import kass.concurrente.modelo.cuchillo.Cuchillo;
import kass.concurrente.modelo.producto.Platillo;

public class Chef extends Persona{ 
    /** Cuchillo utilitario del chef */
    private Cuchillo cu;
   
    /**
     * Constructor de la clase Chef. Inicializa 
     * las variables de clase definidas por la 
     * superclase <code>Persona</code> además de 
     * la variable cuchillo.
     * @param nombre
     * @param edad
     * @param cu
     */
    public Chef(String nombre, Integer edad, Cuchillo cu) {
        super(nombre, edad);
        this.cu = cu;
    }

    public void atiende(Persona cliente, List<Platillo> orden) {
        if(orden.isEmpty())
            System.out.println("Orden vacía\nSiguiente cliente");

        System.out.println("Se ateiende la orden de: " + 
                           cliente.getNombre());
        Double costoTotal = 0.;
        Double tiempoTotal = 0.;
        for (Platillo p : orden) {
            costoTotal += p.calculaPrecio();
            tiempoTotal += p.getTiempoCoccion();
        }
        
    }
}
