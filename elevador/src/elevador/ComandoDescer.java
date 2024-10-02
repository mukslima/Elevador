package elevador;

public class ComandoDescer implements ComandoElevador {
    private Elevador elevador;
    private int andarDestino;

    public ComandoDescer(Elevador elevador, int andarDestino) {
        this.elevador = elevador;
        this.andarDestino = andarDestino;
    }

    @Override
    public void executar() {
        System.out.println("Elevador descendo...");
        elevador.descer(andarDestino);
    }
}
