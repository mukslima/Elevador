package elevador;

public class EstadoEmergencia implements EstadoElevador {
    private Elevador elevador;

    public EstadoEmergencia(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void mover() {
        System.out.println("O elevador está em emergência e não pode se mover!");
    }

    @Override
    public void parar() {
        System.out.println("Elevador já está em emergência.");
    }
}
