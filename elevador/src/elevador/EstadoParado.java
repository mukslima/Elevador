package elevador;

public class EstadoParado implements EstadoElevador {
    private Elevador elevador;

    public EstadoParado(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void mover() {
        // O elevador pode começar a subir ou descer
        System.out.println("Elevador está parado.");
    }

    @Override
    public void parar() {
        System.out.println("Elevador já está parado.");
    }
}
