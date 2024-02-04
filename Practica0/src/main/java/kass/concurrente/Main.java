package kass.concurrente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator; 
import java.util.List;
import java.util.Random;

import kass.concurrente.modelo.cuchillo.CuchilloJapones;
import kass.concurrente.modelo.persona.Chef;
import kass.concurrente.modelo.persona.Persona;
import kass.concurrente.modelo.producto.Platillo;
import kass.concurrente.modelo.producto.Producto;
import kass.concurrente.modelo.producto.ProductoInventario;


/**
 * Clase Main
 * Aqui va toda tu simulacion
 * Genera un par de clientes para que los atienda el chef
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main {
    private static Random rnd = new Random();

    public static void annadeProductos(List<Producto> lista, boolean esInventario) {
        // Feo, pero funciona.
        List<String> prods = Arrays.asList("Pollo", "Tomate", "Cebolla", "Pasta", "Ajo", "Arroz", "Tortilla");
        Iterator<Double> prodsCosts = Arrays.asList(12., 5., 7., 5., 2., 3., 5.).iterator();
        if (esInventario) {
            for (String p : prods) 
                lista.add(new ProductoInventario(p, prodsCosts.next(), rnd.nextInt(3)+1));
        } else {
            for (String p : prods) 
                lista.add(new Producto(p, prodsCosts.next()));
        }
    }

    public static void main(String[] args) {
        ArrayList<Producto> cocina = new ArrayList<>();
        ArrayList<Producto> bodega = new ArrayList<>();
        ArrayList<Platillo> menu   = new ArrayList<>();
        annadeProductos(cocina, false);
        annadeProductos(bodega, true);
        
        List<Producto> receta = Arrays.asList(
            bodega.get(0), bodega.get(1), bodega.get(2),
            bodega.get(4), bodega.get(6)
        );
        menu.add(new Platillo(receta, 20., 1500, "Enchiladas"));
       
        receta = Arrays.asList(
            bodega.get(6), bodega.get(1), bodega.get(2), bodega.get(4)
        );
        menu.add(new Platillo(receta, 15., 500, "Sopa de tortilla"));

        receta = Arrays.asList(
            bodega.get(1), bodega.get(2), bodega.get(3), bodega.get(4)
        );
        menu.add(new Platillo(receta, 10., 300, "Sopa de fideo"));

        receta = Arrays.asList(
            bodega.get(1), bodega.get(2), bodega.get(3), bodega.get(4)
        );
        menu.add(new Platillo(receta, 15., 400, "Arroz rojo"));

        Chef ch1 = new Chef("Jaime", 55, new CuchilloJapones());
        System.out.println("============================= Cafetería Diamond Dogs =============================");
        Persona comensal1 = new Persona("Albert", 22);
        comensal1.setOrden(menu);

        ch1.atiende(comensal1, bodega);
    }
}