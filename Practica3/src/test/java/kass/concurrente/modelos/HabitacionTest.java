package kass.concurrente.modelos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HabitacionTest {
    Habitacion h;
    Prisionero p;
    Prisionero v;

    @BeforeEach
    void setUp(){
        h = new Habitacion();
        p = new Prisionero(0,false,false);
        v = new Vocero(1,true,false);

        h.setPrendido(false);
    }

    @Test
    void switchTest1(){
        try {
            h.entraHabitacion(p);
            assertTrue(h.getPrendido());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void switchTest2(){
        try {
            h.setPrendido(true);
            h.entraHabitacion(v);
            assertFalse(h.getPrendido());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void marcado(){
        try {
            h.entraHabitacion(p);
            assertTrue(p.getMarcado());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void simSimple(){
        try {
            h.entraHabitacion(p);
            h.entraHabitacion(v);
            assertTrue(v.getContador() == 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
