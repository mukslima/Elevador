package elevador;

class EstadoSubindo implements Estado {
    private Elevador elevador;

    public EstadoSubindo(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("Elevador está subindo.");
    }
}
