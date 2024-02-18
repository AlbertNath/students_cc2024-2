import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrices implements Runnable {
    int[][] matrizA;
    int[][] matrizB;
    int[][] resultado;

    // Constructor de la clase Matrices
    public Matrices(int[][] matrizA, int[][] matrizB) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.resultado = new int[matrizA.length][matrizB[0].length];
    }

    // Método para multiplicar matrices de manera concurrente
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

    // Método que genera una matriz con valores pseudoaleatorios
    public static int[][] generarMatrizAleatoria(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(10); // Genera números aleatorios entre 0 y 9
            }
        }
        return matriz;
    }

    // Método para imprimir una matriz
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%4d", matriz[i][j]); // Alineación de 4 caracteres
            }
            System.out.println();
        }
    }


    // Método para multiplicar matrices de manera secuencial
    public static void multiplicaSecuencial(int[][] matrizA, int[][] matrizB, int[][] resultado) {
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizB[0].length; j++) {
                for (int k = 0; k < matrizB.length; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
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
        System.out.println("Prueba secuencial:");
        long startTime = System.currentTimeMillis();
        int[][] resultadoSecuencial = new int[matrizA.length][matrizB[0].length];
        multiplicaSecuencial(matrizA, matrizB, resultadoSecuencial);
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución secuencial: " + (endTime - startTime) + "ms");
        imprimirMatriz(resultadoSecuencial);
        System.out.println(linea);

        System.out.println(linea);
        System.out.println("\nPrueba paralela con 1 hilo:");
        startTime = System.currentTimeMillis();
        Matrices matrices1 = new Matrices(matrizA, matrizB);
        matrices1.multiplicaConcurrente(0, matrizA.length);
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución paralela con 1 hilo: " + (endTime - startTime) + "ms");
        imprimirMatriz(matrices1.resultado);
        System.out.println(linea);
        
        System.out.println(linea);
        System.out.println("\nPrueba paralela con 100 hilos:");
        startTime = System.currentTimeMillis();
        Matrices matrices100 = new Matrices(matrizA, matrizB);
        List<Thread> threads100 = new ArrayList<>();
        int numHilos100 = 100; // Un hilo por cada fila de la matriz A
        for (int i = 0; i < numHilos100; i++) {
            Thread t = new Thread(matrices100, String.valueOf(i));
            threads100.add(t);
            t.start();
        }
        for (Thread thread : threads100) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución paralela con 100 hilos: " + (endTime - startTime) + "ms");
        imprimirMatriz(matrices100.resultado);
        System.out.println(linea);

        System.out.println(linea);
        System.out.println("\nPrueba paralela con 1000 hilos:");
        startTime = System.currentTimeMillis();
        Matrices matrices1000 = new Matrices(matrizA, matrizB);
        List<Thread> threads1000 = new ArrayList<>();
        int numHilos1000 = matrizA.length; // Un hilo por cada fila de la matriz A
        for (int i = 0; i < numHilos1000; i++) {
            Thread t = new Thread(matrices1000, String.valueOf(i));
            threads1000.add(t);
            t.start();
        }
        for (Thread thread : threads1000) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución paralela con 1000 hilos: " + (endTime - startTime) + "ms");
        imprimirMatriz(matrices1000.resultado);
        System.out.println(linea);
    }
}
