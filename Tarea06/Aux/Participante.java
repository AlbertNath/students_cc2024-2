package EjercicioMascotas;

import java.util.Random;

public abstract class Participante implements Runnable {
    public static long MIN_SLEEP_TIME = 100;
    public static long MAX_SLEEP_TIME = 300;
    
    protected Vestidor vestidor;
    private Random random;
    private long vecesEntroVestidor;


    public Participante(Vestidor vestidor) {
        this.vestidor = vestidor;
        this.random = new Random();
        this.vecesEntroVestidor = 0;
    }

    @Override
    public void run() {
        try{
            while (true) {
                entraVestidor();
                decorarMascota();
                dejaVestidor();
                dormirTiempoAleatorio();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public abstract void entraVestidor();
    public abstract void dejaVestidor();

    private void decorarMascota() throws InterruptedException{
        this.vecesEntroVestidor++;
        dormirTiempoAleatorio();
    }

    private void dormirTiempoAleatorio() throws InterruptedException{
        long timeToSleep = Math.abs((MIN_SLEEP_TIME + random.nextInt()) % MAX_SLEEP_TIME);
        Thread.sleep(timeToSleep);
    }
}
