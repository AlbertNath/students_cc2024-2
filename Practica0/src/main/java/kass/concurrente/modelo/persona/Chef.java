package kass.concurrente.modelo.persona;

import java.util.List;
import java.util.stream.Collectors;

import kass.concurrente.modelo.extra.Agregado;
import kass.concurrente.modelo.cuchillo.Cuchillo;
import kass.concurrente.modelo.extra.IngredienteExtra;
import kass.concurrente.modelo.producto.Platillo;
import kass.concurrente.modelo.producto.Producto;
import kass.concurrente.modelo.producto.ProductoInventario;
/** 
 * <p>
 * Clase que representa al Chef de la cafetería. 
 * </p>
 * @author Alberto N. Medel Piña
 * @version 1.0
 */
public class Chef extends Persona{ 
    /** Cuchillo utilitario del chef */
    private Cuchillo cu;
   
    /**
     * Constructor de la clase Chef. Inicializa 
     * las variables de clase definidas por la 
     * superclase <code>Persona</code> además de 
     * la variable cuchillo.
     * @param nombre el nombre del chef.
     * @param edad la edad del chef.
     * @param cu el cuchillo que usará el chef al 
     * atender.
     */
    public Chef(String nombre, Integer edad, Cuchillo cu) {
        super(nombre, edad);
        this.cu = cu;
    }

    /**
     * Método para despachar a un cliente y su orden. 
     * Se fija en el stock del que se dispone, para comprobar
     * existencias de los ingredientes; en caso de no haber, 
     * se irá a conseguir más, lo que implica un retraso en 
     * la ejecución. Atender un platillo solo con nombre y 
     * una lista de requeridos vacía implica un producto.
     * @param cliente
     * @param stock
     */
    public void atiende(Persona cliente, 
                        List<Producto> stock) {
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
            System.out.println("==================================================================================");
            System.out.println("Preparando platillo: " + p.getNombre());
            List<String> requeridos = p.getProductosRequeridos()
                                        .stream().map(x -> x.getNombre())
                                        .collect(Collectors.toList());

            for (String req : requeridos) {
                ProductoInventario pi = (ProductoInventario) stock.stream().filter(x -> x.getNombre()
                                             .equals(req)).findFirst().orElse(null);
                if (pi != null){
                    pi.consumir();
                    System.out.printf("\t>Ingrediente %s costo:\t %2.2f%n", 
                                      pi.getNombre(), pi.getCosto());
                }
            }
            Agregado serv = null;
            // Algunos casos hardcodeados.
            switch (p.getNombre()) {
                case "Enchiladas":
                    serv = new Agregado(
                        new IngredienteExtra("Queso", 3.,(
                            new IngredienteExtra("Crema", 2., p))));
                    break;
                
                case "Sopa de tortilla":
                    serv = new Agregado(
                        new IngredienteExtra("Crema", 3., 
                        new IngredienteExtra("Queso cottage", 7., p)));
                    break;

                case "Arroz rojo":
                    serv = new Agregado(new IngredienteExtra("Vegetales", 2., p));
                    break;

                default:
                    break;
            }
            if(serv != null)
                System.out.printf("Se agregaron los extras: %s%n", serv.getNombre());

            costoTotal += (serv == null)? p.calculaPrecio() : serv.agrega();
            tiempoTotal += p.getTiempoCoccion();
            System.out.println("¡Platillo listo!");
        }
        System.out.println("Total de orden: $" + costoTotal);
        Double tiempoFinal = (tiempoTotal <= cu.corta()) ? 0. : tiempoTotal - cu.corta();
        System.out.println("Tiempo total transcurrido: " + (tiempoFinal));
        System.out.println("==================================================================================");
    }
}
