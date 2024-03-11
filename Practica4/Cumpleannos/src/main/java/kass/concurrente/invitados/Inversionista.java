package kass.concurrente.invitados;

import kass.concurrente.tenedor.Tenedor;
import kass.concurrente.tenedor.TenedorImpl;

/**
 * Clase abstracta que modela al inversionista.
 * El inversionista tiene 2 tenedores a sus lados.
 * El inversionista posee un ID para que se pueda identificar.
 * El inversionista tiene una variable que indica el numero de veces que ha comido.
 * @version 1.0
 * @author Kassandra Mirael
 */
public abstract class Inversionista implements Runnable {

    private Tenedor t1 = new TenedorImpl(1);
    private Tenedor t2 = new TenedorImpl(2);
    private static Integer ID = 0;
    private Integer vecesComido;

    protected Inversionista(){
        this.vecesComido = 0;
        this.ID ++;
    }

    @Override
    public void run() {
        /**
         * El inversionista debe pensar y entrar a la mesa un periodo de veces
         * puesto en el test, agrega el valor aqui.
         */

         for (int i = 0; i < 5; i++) {
            try {
                piensa();
                entraALaMesa();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

    /**
     * Metodo que nos permite entrar a la mesa.
     * El inversionista por fin dejo de pensar y de escribir en su
     * servilleta y se digna en entrar.
     * PRIMERO toma los tenedores.
     * DESPUES come.
     * FINALMENTE los suelta para que los demas los puedan usar.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void entraALaMesa() throws InterruptedException{
        tomaTenedores();
        come();
        sueltaTenedores();
    }

    /**
     * Una vez que termino de pensar sobre finanzas el inversionista
     * se prepara para comer.
     * El inversionista le toma un par de milisegundos comer.
     * ESTA ES LA SECCION CRITICA, SIGNIFICA PELIGRO
     * Incrementa el numero de veces que ha comido.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void come() throws InterruptedException{
        /**
         * Aqui va tu codigo
         */
        System.out.println("Inversionista " + ID + " está comiendo ñam ñam...");
        Thread.sleep(generaTiempoDeEspera());
        this.vecesComido++;
        System.out.println("Inversionista " + ID + " ha comido. Total de veces comidas: " + vecesComido);
    }

    /**
     * Metodo que hace que el inversionista piense.
     * El inversionista pensara por una par de milisegundos.
     * Esto pasa antes de que tome sus tenedores.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void piensa() throws InterruptedException {
        /**
         * Aqui va tu codigo
         */
        System.out.println("Inversionista " + ID + " está pensando...");
        Thread.sleep(generaTiempoDeEspera());
        System.out.println("Inversionista " + ID + " ha terminado de pensar.");
    }

    /**
     * Metodo que nos permite tomar los tenedores.
     * Los toma uno por uno.
     */
    public abstract void tomaTenedores();

    /**
     * Metodo que hace que el inversionista suelte ambos tenedores una vez
     * que terminara de comer. 
     * De esta manera otro los puede usar.
     * Suelta los tenedores uno por uno.
     */
    public abstract void sueltaTenedores();
    
    /**
     * Metodo que genera un numero pseudoaleatorio entre 1 y 10
     * @return El tiempo de espera
     */
    private long generaTiempoDeEspera(){
        double i=Math.random()*10.0;
        return (long)i ;
    }

    /*
     * Rellena Getter and Setters primero
     * Documenta los metodos.
     * Cuando acabes borra estew comentario
     */
    public int getId(){
        return 0;
    }

    public void setId(int id){

    }

    public Tenedor getTenedorIzq(){
        return this.t1;
    }

    public void setTenedorIzq(Tenedor t){

    }

    public Tenedor getTenedorDer(){
        return this.t2;
    }

    public void setTenedorDer(Tenedor t){

    }

    public int getVecesComido(){
        return this.vecesComido;
    }

    public void setVecesComido(int vecesComido){
        
    }
}
