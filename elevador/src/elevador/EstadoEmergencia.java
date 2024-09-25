package elevador;

class EstadoEmergencia implements Estado {
    private Elevador elevador;

    public EstadoEmergencia(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("O elevador está em emergência e não pode operar.");
    }
}
