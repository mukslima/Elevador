package elevador;

class EstadoParado implements Estado {
    private Elevador elevador;

    public EstadoParado(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("Elevador está parado.");
    }
}
