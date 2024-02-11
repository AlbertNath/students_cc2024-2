package kass.concurrente.hilos;


public class Hilos implements Runnable {
    
    @Override
    public void run() { //Sobrescribimos el metodo run
        int a = 10;
        int b = 12;
        int ID = Integer.parseInt(Thread.currentThread().getName());
        if(ID == 1){
            System.out.println("Soy el hilo 1");
        }else{
            System.out.println("Hola soy el: "+ Thread.currentThread().getName());//Pedimos el nombre del hilo pidiendo primero que se seleccione el Hilo
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Hilos h = new Hilos();//Se crea una instancia de la clase
        Thread t1 = new Thread("1");//Creamos un hilo, le pasamos de parametro la instancia de la clase y un nombre
        Thread t2 = new Thread(h,"2");
        Thread t3 = new Thread(h,"25");
        Thread t4 = new Thread(h,"45");
        
        t1.start();t2.start();t3.start();t4.start(); //Se inicializan los hilos para comenzar su ejecucion

        t1.join();t2.join();t3.join();t4.join();//????
        Integer totalHilos = 10;
        Thread[] hilos = new Thread[totalHilos];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(h, ""+i);
            hilos[i].start();
        }

        for (Thread i : hilos) 
            i.join();
    }
}
