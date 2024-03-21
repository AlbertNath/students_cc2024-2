package kass.concurrente.invitados;

import kass.concurrente.candados.Semaphore;

/**
 * Clase que modela al inversionista, pero esta vez
 * usando el filtro.
 * No se sobreescribe el run, si hicieron bien las cosas
 * Entonces se pasara sin problemas para aca
 * Good Luck!
 * @version 1.1
 * @author Kassandra Mirael
 */
public class InversionistaFiltro extends Inversionista {

    private Semaphore filtro;

    /*
     * Crea una instancia del InversionistaFiltro
     */
    public InversionistaFiltro(Semaphore filtro){
        this.filtro = filtro;
    }

    @Override
    public void entraALaMesa() throws InterruptedException{
        filtro.acquire();
        come();
        filtro.release();
    }

    @Override
    public void tomaTenedores() {
        t1.tomar();
        t2.tomar();
    }

    @Override
    public void sueltaTenedores() {
        t1.soltar();
        t2.soltar();
    }
    
}
