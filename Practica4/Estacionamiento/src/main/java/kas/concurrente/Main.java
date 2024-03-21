package kas.concurrente;

import java.util.ArrayList;
import java.util.List;


import kas.concurrente.modelos.Estacionamiento;


/**
 * Clase principal, la usaran para SUS pruebas
 * Pueden modigicar los valores estaticos para ver como funciona
 * NO USEN VALORES EXTREMEDAMENTE ALTOS, puede alentar mucho su compu
 * AQUI EJECUTAN LA SIMULACION
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main implements Runnable{


    private Estacionamiento estacionamiento;
 
    /**
     * Metodo constructor
     * Se inicializa el Semaforo Modificado con _______
     * Se inicaliza el Estacionamiento con _______
     */
    public Main(){
        /**
         * Aqui va tu codigo
         */
    }

    /**
     * Una documentacion del main xD, esta bien 
     * Paso 0: Lee estas instrucciones
     * Paso 1: Crea el Objeto de tipo main
     * Paso 2: Crea Una estructura de datos que contenga a nuestros hilos
     * Paso 3: Genera con un ciclo, el cual inialice un numero igual de NUM_CARROS
     * Paso 4: No olvides agregarlos a la estructura e inicializarlos
     * Paso 5: Finalmente has un Join a tus hilos
     * @param args Los Argumentos
     * @throws InterruptedException Por si explota su compu al ponerle medio millon de hilos xD
     */
    public static void main(String[] args) throws InterruptedException{
        Main main = new Main(); // Paso 1: Crea el Objeto de tipo Main

        // Paso 2: Crea una estructura de datos que contenga a nuestros hilos
        List<Thread> hilos = new ArrayList<>();

        // Paso 3: Genera con un ciclo, el cual inicialice un número igual de NUM_CARROS
        final int NUM_CARROS = 10; // Número de carros a simular
        for (int i = 0; i < NUM_CARROS; i++) {
            Thread hilo = new Thread(main); // Crear un nuevo hilo
            hilos.add(hilo); // Agregar el hilo a la lista de hilos
            hilo.start(); // Iniciar el hilo
        }

        // Paso 4: Finalmente haz un Join a tus hilos
        for (Thread hilo : hilos) {
            hilo.join(); // Esperar a que todos los hilos terminen
        }
    }

    @Override
    public void run() {
    try {
        estacionamiento.entraCarro((int) Thread.currentThread().getId());
    } catch (InterruptedException e) {
        // Re-lanzar la excepción InterruptedException
        Thread.currentThread().interrupt();
        e.printStackTrace();
    }
}
}
