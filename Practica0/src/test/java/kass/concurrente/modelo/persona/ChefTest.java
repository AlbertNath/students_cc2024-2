package kass.concurrente.modelo.persona;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kass.concurrente.modelo.cuchillo.Cuchillo;
import kass.concurrente.modelo.cuchillo.CuchilloAcero;

class ChefTest {
    Chef c;
    Cuchillo cu;

    @BeforeEach
    void setUp(){
        //generar cu, es interfaz, si no no funcionara
        cu = new CuchilloAcero();
        c = new Chef("Nombre",10,cu);
    }

    @Test
    void constructorTest(){
        assertNotNull(c);
    }
}
