package elevador;

class ComandoAbrirPorta implements ComandoElevador {
    private Elevador elevador;

    public ComandoAbrirPorta(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.abrirPorta();
    }
}
