package kass.concurrente.modelo.persona;

import java.util.List;

import kass.concurrente.modelo.producto.Platillo;

/**
 * Clase que modela una persona
 * @author Kassandra Mirael
 */
public class Persona {
    /** El nombre de la persona */
    protected String nombre;
    /** La edad de la persona */
    protected Integer edad;
    /** La orden de una persona de un platillo o más */
    private List<Platillo> orden;

    /**
     * Constructor de la clase. Inicializa 
     * la lista de orden como nula.
     * @param nombre el nombre de la persona.
     * @param edad la edad de la persona.
     */
    public Persona(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.orden = null;
    }

    /**
     * Método para obtener el nombre de la persona.
     * @return el nombre de la persona.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Método para obtener la edad de la persona.
     * @return la edad de la persona
     */
    public Integer getEdad() {
        return this.edad;
    } 

    /**
     * Método para definir la orden de una persona
     * @param orden la orden del cliente.
     */
    public void setOrden(List<Platillo> orden) {
        this.orden = orden;
    }

    /**
     * Método para obtener la orden del cliente.
     * @return la orden del cliente.
     */
    public List<Platillo> getOrden() {
        return this.orden;
    }
}
