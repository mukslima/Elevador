package elevador;

public class ComandoEmergencia implements Comando {
    private Elevador elevador;

    public ComandoEmergencia(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.entrarEmEmergencia(); // Agora acessível
    }
}
