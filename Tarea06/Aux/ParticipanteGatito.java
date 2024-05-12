public class ParticipanteGatito extends Participante{

    public ParticipanteGatito(Vestidor vestidor){
        super(vestidor);
    }

    @Override
    public void entraVestidor() {
        this.vestidor.enterCat();
    }

    @Override
    public void dejaVestidor() {
        this.vestidor.leaveCat();
    }
    
}
