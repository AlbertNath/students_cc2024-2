package kass.concurrente.modelo.persona;

import java.util.List;

import kass.concurrente.modelo.producto.Platillo;

/**
 * Clase que modela una persona
 * @author Kassandra Mirael
 */
public class Persona {
    protected String nombre;
    protected Integer edad;
    private List<Platillo> orden;

    public Persona(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getEdad() {
        return this.edad;
    } 

    public void setOrden(List<Platillo> orden) {
        this.orden = orden;
    }

    public List<Platillo> getOrden() {
        return this.orden;
    }
}
