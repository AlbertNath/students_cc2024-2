package kas.concurrente;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Implementacion del candado ALock
 * @version 1.0
 * @author PaoPaotrol
 */
public class ALock implements Lock{
    ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };    
    AtomicInteger tail;
    volatile boolean[] flag;
    int size;

    /**
     * Instancia del candado ALock
     * @param hilos -- int numero de hilos
     */
    public ALock(int hilos){
        size = hilos;
        tail = new AtomicInteger(0);
        flag = new boolean[size];
        flag[0] = true;
    }

    @Override
    public void lock() {
        int slot = tail.getAndIncrement() % size;
        mySlotIndex.set(slot);
        while (!flag[slot]) Thread.yield();
    }

    @Override
    public void unlock() {
        int slot = mySlotIndex.get();
        flag[slot] = false;
        flag[(slot + 1) % size] = true;
    }
    
}