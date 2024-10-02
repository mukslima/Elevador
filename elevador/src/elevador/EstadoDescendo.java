package elevador;

public class EstadoDescendo implements EstadoElevador {
    private Elevador elevador;

    public EstadoDescendo(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void mover() {
        System.out.println("Elevador descendo...");
    }

    @Override
    public void parar() {
        elevador.setEstado(new EstadoParado(elevador));
        System.out.println("Elevador parou.");
    }
}
