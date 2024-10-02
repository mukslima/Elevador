package elevador;

public class EstadoSubindo implements EstadoElevador {
    private Elevador elevador;

    public EstadoSubindo(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void mover() {
        System.out.println("Elevador subindo...");
    }

    @Override
    public void parar() {
        elevador.setEstado(new EstadoParado(elevador));
        System.out.println("Elevador parou.");
    }
}
