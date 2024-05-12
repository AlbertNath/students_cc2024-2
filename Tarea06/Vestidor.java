import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Vestidor {
    private final Lock lock = new ReentrantLock();
    private final Condition dogCondition = lock.newCondition();
    private final Condition catCondition = lock.newCondition();
    private int dogsInLocker = 0;
    private int catsInLocker = 0;

    public void enterDog() throws InterruptedException {
        lock.lock();
        try {
            while (catsInLocker > 0) {
                dogCondition.await();
            }
            dogsInLocker++;
        } finally {
            lock.unlock();
        }
    }

    public void leaveDog() {
        lock.lock();
        try {
            dogsInLocker--;
            if (dogsInLocker == 0) {
                catCondition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void enterCat() throws InterruptedException {
        lock.lock();
        try {
            while (dogsInLocker > 0) {
                catCondition.await();
            }
            catsInLocker++;
        } finally {
            lock.unlock();
        }
    }

    public void leaveCat() {
        lock.lock();
        try {
            catsInLocker--;
            if (catsInLocker == 0) {
                dogCondition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Vestidor concurso = new Vestidor();

        // Thread para perro
        Thread dogThread = new Thread(() -> {
            try {
                concurso.enterDog();
                System.out.println("Perro entrando al vestidor...");
                Thread.sleep(2000); // Simula tiempo en el vestidor
                concurso.leaveDog();
                System.out.println("Perro saliendo del vestidor...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread para gato
        Thread catThread = new Thread(() -> {
            try {
                concurso.enterCat();
                System.out.println("Gato entrando al vestidor...");
                Thread.sleep(3000); // Simula tiempo en el vestidor
                concurso.leaveCat();
                System.out.println("Gato saliendo del vestidor...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Iniciar threads
        dogThread.start();
        catThread.start();

        // Esperar a que terminen los threads
        try {
            dogThread.join();
            catThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
