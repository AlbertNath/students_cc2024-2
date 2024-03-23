package kass.concurrente.invitados;

import kass.concurrente.candados.Semaphore;

import kass.concurrente.tenedor.Tenedor;

import java.util.logging.Logger;

/**
 * Clase abstracta que modela al inversionista.
 * El inversionista tiene 2 tenedores a sus lados.
 * El inversionista posee un ID para que se pueda identificar.
 * El inversionista tiene una variable que indica el numero de veces que ha comido.
 * @version 1.0
 * @author Kassandra Mirael
 */
public abstract class Inversionista implements Runnable {

    protected Tenedor t1;
    protected Tenedor t2;
    protected Integer id;
    protected Integer vecesComido = 0;
    protected Semaphore semaforo;
    private static final Logger LOGGER = Logger.getLogger(Inversionista.class.getName());

    /**
     * Constructor de Inversionista
     */
    protected Inversionista(){
        this.vecesComido = 0;
    }

    @Override
    public void run() {
        /**
         * El inversionista debe pensar y entrar a la mesa un periodo de veces
         * puesto en el test, agrega el valor aqui.
         */

         for (int i = 0; i < 500; i++) {
            try {
                piensa();
                entraALaMesa();
            } catch (InterruptedException e) {
                //e.printStackTrace();
                Thread.currentThread().interrupt();
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
     * @throws InterruptedException se lanzará si el hilo que ejecuta este método es interrumpido
     * mientras está en espera, por ejemplo, si otro hilo llama al método interrupt() en el hilo 
     * que ejecuta este método.
     */
    public void entraALaMesa() throws InterruptedException{
        come();
    }

    /**
     * Una vez que termino de pensar sobre finanzas el inversionista
     * se prepara para comer.
     * El inversionista le toma un par de milisegundos comer.
     * ESTA ES LA SECCION CRITICA, SIGNIFICA PELIGRO
     * Incrementa el numero de veces que ha comido.
     * @throws InterruptedException <se lanzará si el hilo que ejecuta este método es 
     * interrumpido mientras está en espera, por ejemplo, si otro hilo llama al método
     * interrupt() en el hilo que ejecuta este método.>
     */
    public void come() throws InterruptedException{
        LOGGER.info("Inversionista " + id + " está comiendo ñam ñam...");
        Thread.sleep(generaTiempoDeEspera());
        this.tomaTenedores();
        this.vecesComido++;
        this.sueltaTenedores();
        LOGGER.info("Inversionista " + id + " ha comido. Total de veces comidas: " + vecesComido);
    }

    /**
     * Metodo que hace que el inversionista piense.
     * El inversionista pensara por una par de milisegundos.
     * Esto pasa antes de que tome sus tenedores.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void piensa() throws InterruptedException {
        LOGGER.info("Inversionista " + id + " está pensando...");
        Thread.sleep(generaTiempoDeEspera());
        LOGGER.info("Inversionista " + id + " ha terminado de pensar.");
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
    /**
     * Metodo que obtiene el ID del inversionista
     * @return El ID del inversionista
     */
    public int getId(){
        return id;
    }

    /**
     * Metodo que cambia el ID del inversionista
     * @param id El nuevo ID del inversionista
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Metodo que obtiene el tenedor izquierdo
     * @return El tenedor izquierdo
     */
    public Tenedor getTenedorIzq(){
        return this.t1;
    }

    /**
     * Metodo que cambia el tenedor izquierdo
     * @param t El nuevo tenedor izquierdo
     */
    public void setTenedorIzq(Tenedor t){
        this.t1 = t;
    }

    /**
     * Metodo que obtiene el tenedor derecho
     * @return El tenedor derecho
     */
    public Tenedor getTenedorDer(){
        return this.t2;
    }

    /**
     * Metodo que cambia el tenedor derecho
     * @param t El nuevo tenedor derecho
     */
    public void setTenedorDer(Tenedor t){
        this.t2 = t;
    }

    /**
     * Metodo que obtiene el número de veces que el inversionita ha comido
     * @return El número de veces comido
     */
    public int getVecesComido(){
        return this.vecesComido;
    }

    /**
     * Metodo que cambia el número de veces que el inversionita ha comido
     * @param vecesComido El nuevo número de veces comido
     */
    public void setVecesComido(int vecesComido){
        this.vecesComido = vecesComido;
    }
}
