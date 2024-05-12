package EjercicioMascotas;

public class ParticipantePerrito extends Participante {

    public ParticipantePerrito(Vestidor vestidor) {
        super(vestidor);
    }

    @Override
    public void entraVestidor() {
        this.vestidor.enterDog();
    }

    @Override
    public void dejaVestidor() {
        this.vestidor.leaveDog();
    }
    
}
