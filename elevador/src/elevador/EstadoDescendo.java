package elevador;

class EstadoDescendo implements Estado {
    private Elevador elevador;

    public EstadoDescendo(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("Elevador está descendo.");
        // A variável 'elevador' agora é usada para informar o andar
        System.out.println("Andar atual: " + elevador.getAndarAtual());
    }
}
