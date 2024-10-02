package elevador;

public class ComandoManutencao implements ComandoElevador {
    private Elevador elevador;

    public ComandoManutencao(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.entrarEmManutencao(); 
    }
}
