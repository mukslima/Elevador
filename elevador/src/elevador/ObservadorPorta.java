package elevador;

class ObservadorPorta implements Observador {
    private Elevador elevador;

    public ObservadorPorta(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void atualizar() {
        if (elevador.getEstado() instanceof EstadoParado) {
            elevador.abrirPorta();  // Porta abre automaticamente quando o elevador para
        } else if (elevador.getEstado() instanceof EstadoSubindo || elevador.getEstado() instanceof EstadoDescendo) {
            elevador.fecharPorta(); // Porta fecha automaticamente ao iniciar movimento
        }
    }
}
