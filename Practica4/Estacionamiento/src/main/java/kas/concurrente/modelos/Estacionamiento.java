package kas.concurrente.modelos;

import java.util.Random;
import java.util.logging.Logger;

import kas.concurrente.constante.Contante;

/**
 * En esta clase se simula el estacionamiento en si
 * Posee un conjunto de arreglos de tipo Lugar (o arreglo bidimensional?)
 * Posee un entero de lugaresDisponibles (Se podra hacer por pisos?) (Habra otra manera de hacerlo mejor?)
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Estacionamiento {

    private Lugar[][] lugares;
    private volatile int lugaresDisponibles;
    final Logger logger = Logger.getLogger(Estacionamiento.class.getName());
    Random random = new Random();


    /**
     * Metodo constructor
     * Modifica el constructor o crea otro segun consideres necesario
     * @param capacidad La capacidad del estacionamiento
     */
    public Estacionamiento(int pisos, int capacidadPiso){
        lugares = new Lugar[pisos][capacidadPiso];
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < capacidadPiso; j++) {
                lugares[i][j] = new Lugar(i * capacidadPiso + j);                
            }
        }
        this.lugaresDisponibles = pisos * capacidadPiso;
    }
    /**
     * Obtiene el atributo de lugaresDisponibles
     * @return --- int atributo lugaresDisponibles
     */
    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    /**
     * Asigna un nuevo valor al atributo lugaresDisponible
     * @param lugaresDisponibles --- int nuevo valor para el atributo
     */
    public void setLugaresDisponibles(int lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    /**
     * Metodo que nos indica si esta lleno el estacionamiento
     * @return true si esta lleno, false en otro caso
     */
    public boolean estaLleno(){
        return lugaresDisponibles == 0;
    }

    /**
     * Metodo en el que se simula la entrada de un carro
     * Imprime un texto que dice que el carro a entrado de color AZUL
     * @param nombre El nombre del carro
     * @throws InterruptedException Si llega a fallar
     */
    public void entraCarro(int nombre) throws InterruptedException{
        logger.info(Contante.AZUL + "El carro " + nombre + " ha entrado al estacionamiento");
        asignaLugar(obtenLugar());
    }

    /**
     * Metodo que asigna el lugar, una vez asignado ESTACIONA su nave
     * @param lugar El lugar que correspone
     * @throws InterruptedException
     */
    public void asignaLugar(int lugar) throws InterruptedException {
        int piso = lugar / lugares[0].length;
        int lugarPiso = lugar % lugares[0].length;
        Lugar lugarI = lugares[piso][lugarPiso];
        lugarI.estaciona();
        lugaresDisponibles--;
    }

    /**
     * Se obtiene un lugar de forma pseudoAleatoria
     * Aqui necesito que revisen el repaso de estadistica que mande en 
     * repaso, quiero que expliquen porque lo pedimos en forma pseudoAleatoria
     * @return Retorna el indice del lugar
     */
    public int obtenLugar() {
    int maximo = lugares.length * lugares[0].length;
    return random.nextInt(maximo);
    }

    public Lugar [][] getLugares(){
        return lugares;
    }
}