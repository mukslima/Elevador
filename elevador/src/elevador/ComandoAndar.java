package elevador;

public class ComandoAndar implements ComandoElevador {
    private Elevador elevador;
    private int andarDesejado;

    public ComandoAndar(Elevador elevador, int andarDesejado) {
        this.elevador = elevador;
        this.andarDesejado = andarDesejado;
    }

    @Override
    public void executar() {
        if (andarDesejado > elevador.getAndarAtual()) {
            elevador.subir(andarDesejado);
        } else if (andarDesejado < elevador.getAndarAtual()) {
            elevador.descer(andarDesejado);
        } else {
            System.out.println("Já estamos no andar " + andarDesejado + ".");
        }
    }
}

