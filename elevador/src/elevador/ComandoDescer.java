package elevador;

class ComandoDescer implements Comando {
    private Elevador elevador;
    private int andarDestino;

    public ComandoDescer(Elevador elevador, int andarDestino) {
        this.elevador = elevador;
        this.andarDestino = andarDestino;
    }

    @Override
    public void executar() {
        elevador.requisitarAndar(andarDestino); // Requisita descer até o andar destino
    }
}