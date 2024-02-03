package kass.concurrente.modelo.persona;

import java.util.List;
import java.util.stream.Collectors;

import kass.concurrente.modelo.cuchillo.Cuchillo;
import kass.concurrente.modelo.extra.Agregado;
import kass.concurrente.modelo.extra.Crema;
import kass.concurrente.modelo.extra.Queso;
import kass.concurrente.modelo.producto.Platillo;
import kass.concurrente.modelo.producto.ProductoInventario;

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

    public void atiende(Persona cliente, 
                        List<ProductoInventario> stock) {
        List<Platillo> orden = cliente.getOrden();
        if(orden.isEmpty()){
            System.out.println("Orden vacía\nSiguiente cliente");
            return;
        }

        System.out.println("Se ateiende la orden de: \t" + 
                           cliente.getNombre());
        Double costoTotal = 0.;
        Double tiempoTotal = 0.;
        for (Platillo p : orden) {
            List<String> requeridos = p.getProductosRequeridos()
                                        .stream().map(x -> x.getNombre())
                                        .collect(Collectors.toList());

            for (String req : requeridos) {
                ProductoInventario pi = stock.stream().filter(x -> x.getNombre()
                                             .equals(req)).findFirst().orElse(null);
                if (pi != null)
                    pi.consumir();
            }
            Agregado serv = null;
            switch (p.getNombre()) {
                case "Enchiladas":
                    serv = new Agregado(new Queso(new Crema(p)));
                    break;
            
                default:
                    break;
            }

            costoTotal += (serv == null)? p.calculaPrecio() : serv.agrega();
            tiempoTotal += p.getTiempoCoccion();
        }
        System.err.println("Total de orden: " + costoTotal);
        System.err.println("Tiempo transcurrido: " + (tiempoTotal - cu.corta()));
    }
}
