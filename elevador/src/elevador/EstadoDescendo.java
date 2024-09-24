package elevador;

class EstadoDescendo implements Estado {
    private Elevador elevador;

    public EstadoDescendo(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("Elevador está descendo.");
    }
}
