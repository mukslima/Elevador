package elevador;

public class EstadoManutencao implements EstadoElevador {
    private Elevador elevador;

    public EstadoManutencao(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void mover() {
        System.out.println("O elevador está em manutenção e não pode se mover.");
    }

    @Override
    public void parar() {
        System.out.println("Elevador já está em manutenção.");
    }
}
