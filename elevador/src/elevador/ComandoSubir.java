package elevador;

public class ComandoSubir implements ComandoElevador {
    private Elevador elevador;
    private int andarDestino;

    public ComandoSubir(Elevador elevador, int andarDestino) {
        this.elevador = elevador;
        this.andarDestino = andarDestino;
    }

    @Override
    public void executar() {
        System.out.println("Elevador subindo...");
        elevador.subir(andarDestino);
    }
}
