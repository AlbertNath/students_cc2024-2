import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Clase para representación de matrices. En general 
 * martices de <i>n * m</i>, con la operación de 
 * multiplicación disponible. 
 */
public class Matrices implements Runnable {
    /** Primera matriz */
    int[][] matrizA;
    /** Segunda matriz */
    int[][] matrizB;
    /** Matriz resultado */
    int[][] resultado;
    /** Generador de números pseudoaleatorios */
    static Random rnd = new Random();

    /**
     * Constructor de la clase Matrices.
     * @param matrizA primera matriz con la que operar.
     * @param matrizB segunda matriz con la que operar.
     */
    public Matrices(int[][] matrizA, int[][] matrizB) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.resultado = new int[matrizA.length][matrizB[0].length];
    }

    /**
     * Método para multiplicar matrices de manera concurrente
     * @param filaInicio índce de la fila inicial.
     * @param filaFin ínidice de la fila final.
     */
    public void multiplicaConcurrente(int filaInicio, int filaFin) {
        for (int i = filaInicio; i < filaFin; i++) {
            for (int j = 0; j < matrizB[0].length; j++) {
                for (int k = 0; k < matrizB.length; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }

    @Override
    public void run() {
        int filaInicio = Integer.parseInt(Thread.currentThread().getName());
        int filaFin = filaInicio + 1;
        multiplicaConcurrente(filaInicio, filaFin);
    }

    /**
     * Método que genera una matriz con valores pseudoaleatorios
     * @param filas cantidad de filas deseadas.
     * @param columnas cantidad de columnas deseadas.
     * @return una matriz de dimensiones <i>filas * columnas</i>.
     * @throws InterruptedException si algún hilo sufre un error.
     */
    public static int[][] generarMatrizAleatoria(int filas, int columnas) throws InterruptedException {
        int[][] matriz = new int[filas][columnas];
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            final int fila = i; 
            Thread t = new Thread(() -> {
                for (int j = 0; j < columnas; j++) {
                    matriz[fila][j] = rnd.nextInt(10); // Genera números aleatorios entre 0 y 9
                }
            });
            hilos.add(t);
            t.start();
        }
        for (Thread thread : hilos) {
            thread.join();
        }
        return matriz;
    }
    

    /**
     * Método para imprimir una matriz
     * @param matriz matriz a imprimr.
     */
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%4d", matriz[i][j]); // Alineación de 4 caracteres
            }
            System.out.println();
        }
    }


    /**
     * Método que inicializa una canitdad dada de 
     * instancias de clase <code>Matrices</code> 
     * como hilos.
     * @param m la instanca concreta de la clase 
     * <code>Matrices</code>.
     * @param numHilos la cantiad de hilos deseada.
     * @param matrizIzq la matriz izquierda de la 
     * operación.
     * @throws InterruptedException si algún hilo sufre un error.
     */
    private static void iniciaHilos(Matrices m, int numHilos, int[][] matrizIzq) throws InterruptedException {
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < matrizIzq.length; i++) {
            Thread t = new Thread(m, String.valueOf(i));
            hilos.add(t);
            t.start();
            if(hilos.size() == numHilos) {
                for (Thread thread : hilos)
                    thread.join();
                hilos.clear();
            }
        }
    }
   
    /**
     * Realiza una ejecución de la operación <i>multiplica</i>
     * sobre dos matrices con una cantidad de hilos dada.
     * Imprime el resultado y el tiempo en milisegundos 
     * transucrrido.
     * @param numHilos la cantiad de hilos deseada.
     * @param mA primera matriz con la que oprerar.
     * @param mB segunda matriz con la que oprerar.
     */
    private static void ejecuta(int numHilos, int[][] mA, int[][] mB) {
        String linea = "————————————————————————————————————————————————————————";
        String msg = "\nPrueba paralela con %d hilo";
        msg += (numHilos == 1)? " | sequencial \n" : "s \n";
        System.out.printf(msg, numHilos);
        long startTime = System.nanoTime();
        Matrices m = new Matrices(mA, mB);
        try {
            iniciaHilos(m, numHilos, mA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        msg = "Tiempo de ejecución paralela con %d hilo";
        long et = endTime - startTime;
        msg += (numHilos == 1)? ": " + et + "ms\n" :
                                "s: " + et + "ms\n";
        System.out.printf(msg, numHilos);
        imprimirMatriz(m.resultado);
        System.out.println(linea);
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] matrizA = generarMatrizAleatoria(3, 3);
        int[][] matrizB = generarMatrizAleatoria(3, 3);
        String linea = "————————————————————————————————————————————————————————";
        System.out.println(linea);
        System.out.println("Matriz A :");
        imprimirMatriz(matrizA);
        System.out.println("Matriz B :");
        imprimirMatriz(matrizB);
        System.out.println(linea);
        System.out.println(linea);
    
        ejecuta(1, matrizA, matrizB);
        ejecuta(100, matrizA, matrizB);
        ejecuta(1000, matrizA, matrizB);
        // Más rápido que disparar un hilo????:
        //System.out.println(linea);
        //System.out.println("\nPrueba paralela con 1 hilo | secuencial:");
        //long startTime = System.nanoTime();
        //Matrices matrices1 = new Matrices(matrizA, matrizB);
        ////matrices1.multiplicaConcurrente(0, matrizA.length); Es más rápido???
        //try {
        //    iniciaHilos(matrices1, 1, matrizA);
        //} catch (Exception e) {
        //    System.err.println(e);
        //}
        //long endTime = System.nanoTime();
        //System.out.println("Tiempo de ejecución paralela con 1 hilo: " + (endTime - startTime) + "ms");
        //imprimirMatriz(matrices1.resultado);
        //System.out.println(linea);
    }
}
