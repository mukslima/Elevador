package elevador;

class ComandoFecharPorta implements ComandoElevador {
    private Elevador elevador;

    public ComandoFecharPorta(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void executar() {
        elevador.fecharPorta();
    }
}