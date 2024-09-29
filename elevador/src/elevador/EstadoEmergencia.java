package elevador;

class EstadoEmergencia implements Estado {
    private Elevador elevador;

    public EstadoEmergencia(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        System.out.println("Elevador está em emergência.");
    }
}