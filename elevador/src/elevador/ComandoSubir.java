package elevador;

class ComandoSubir implements Comando {
    private Elevador elevador;
    private int andarDestino;

    public ComandoSubir(Elevador elevador, int andarDestino) {
        this.elevador = elevador;
        this.andarDestino = andarDestino;
    }

    @Override
    public void executar() {
        elevador.requisitarAndar(andarDestino); // Requisita subir até o andar destino
    }
}
