package elevador;

public class ComandoManutencao implements Comando {
    private Elevador elevador;

    public ComandoManutencao(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.entrarEmManutencao(); // Agora acessível
    }
}
