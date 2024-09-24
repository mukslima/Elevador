package elevador;

class PainelControle implements Observador {
    private Elevador elevador;

    public PainelControle(Elevador elevador) {
        this.elevador = elevador;
    }

    @Override
    public void atualizar() {
        System.out.println("Atualizando painel. Elevador está no andar: " + elevador.getAndarAtual());
    }
}
