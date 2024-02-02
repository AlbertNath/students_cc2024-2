package kass.concurrente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public static void main(String[] args) {
        ArrayList<ProductoInventario> bodega;
        ArrayList<Platillo> menu;
        bodega = new ArrayList<>(); 
        bodega.add(new ProductoInventario("Pollo", 12., 0));
        bodega.add(new ProductoInventario("Tomate", 5., 10));
        bodega.add(new ProductoInventario("Cebolla", 7., 10));
        bodega.add(new ProductoInventario("Pasta", 5., 7));
        bodega.add(new ProductoInventario("Ajo", 2., 4));
        bodega.add(new ProductoInventario("Arroz", 3., 3));
        bodega.add(new ProductoInventario("Tortilla", 5., 6));
       
        menu = new ArrayList<>(); 
        List<Producto> receta = Arrays.asList(
        bodega.get(0), bodega.get(1), bodega.get(2),
        bodega.get(4), bodega.get(6));
        menu.add(new Platillo(receta, 15., 1500, "Enchiladas"));
       
        receta = Arrays.asList(
        bodega.get(6), bodega.get(1), bodega.get(2), bodega.get(4));
        menu.add(new Platillo(receta, 15., 500, "Sopa de tortilla"));

        Chef ch1 = new Chef("Jaime", 55, new CuchilloJapones());
        Persona comensal1 = new Persona("Albert", 22);
        comensal1.setOrden(menu);

        ch1.atiende(comensal1, bodega);
    }
}