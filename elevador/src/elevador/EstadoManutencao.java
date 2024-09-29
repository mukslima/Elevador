package elevador;

class EstadoManutencao implements Estado {
    private Elevador elevador;

    public EstadoManutencao(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("Elevador está em manutenção.");
    }
}