package EjercicioMascotas;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vestidor{
    private volatile long vecesEntroPerrito;
    private volatile long vecesEntroGatito;

    private volatile long perritos;
    private volatile long gatitos;

    private boolean usado;

    private Lock lock;

    private Condition conPerrito;
    private Condition conGatito;

    public Vestidor(){
        this.vecesEntroPerrito = 0;
        this.vecesEntroGatito = 0;
        this.perritos = 0;
        this.gatitos = 0;
        usado = false;
        lock = new ReentrantLock();
        conPerrito = lock.newCondition();
        conGatito = lock.newCondition();
    }

    public void enterDog(){
        lock.lock();
        try{
            while (!usado) {
                conPerrito.await();
            }
            usado = true;
            vecesEntroPerrito++;
            ++perritos;
        }finally{
            lock.unlock();
        }

    }

    public void leaveDog(){
        lock.lock();
        try{
            --perritos;
            if(perritos==0){
                conGatito.signalAll();
                usado = false;
            }else{
                conGatito.signal();//Solo es para parte practica, en la teoria no es necesario
            }
        }finally{
            lock.unlock();
        }
    }

    public void enterCat(){
        lock.lock();
        try{
            while(usado){
                conGatito.await();
            }
            usado = false;
            gatitos++;
            vecesEntroGatito++;
        }finally{
            lock.unlock();
        }
    }

    public void leaveCat(){
        lock.lock();
        try{
            gatitos--;
            if(gatitos == 0){
                conPerrito.signalAll();
                usado = true;
            }else{
                conPerrito.signal();
            }
        }finally{
            lock.unlock();
        }
    }
}