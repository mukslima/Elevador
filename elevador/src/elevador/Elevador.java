package elevador;

public class Elevador {
    private int andarAtual = 0;
    private boolean portaAberta = false;
    private EstadoElevador estado;

    public Elevador() {
        this.estado = new EstadoParado(this); // Inicialmente parado
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public boolean isPortaAberta() {
        return portaAberta;
    }

    public void abrirPorta() {
        portaAberta = true;
        System.out.println("Elevador parou, abrindo porta...");
    }

    public void fecharPorta() {
        portaAberta = false;
        System.out.println("Elevador fechando porta...");
    }

    public void subir(int andarDestino) {
        while (andarAtual < andarDestino) {
            andarAtual++;
            System.out.println("Elevador subindo...");
            System.out.println("Andar atual: " + andarAtual);

            if (andarAtual == andarDestino) {
                abrirPorta();
                System.out.println("Pessoa entrando ou saindo...");
                fecharPorta();
            }
        }
    }

    public void descer(int andarDestino) {
        while (andarAtual > andarDestino) {
            andarAtual--;
            System.out.println("Elevador descendo...");
            System.out.println("Andar atual: " + andarAtual);

            if (andarAtual == andarDestino) {
                abrirPorta();
                System.out.println("Pessoa entrando ou saindo...");
                fecharPorta();
            }
        }
    }

    public EstadoElevador getEstado() {
        return estado;
    }

    public void setEstado(EstadoElevador estado) {
        this.estado = estado;
    }

    public void entrarEmEmergencia() {
        this.estado = new EstadoEmergencia(this);
        System.out.println("Elevador entrou em estado de emergência!");
    }

    public void entrarEmManutencao() {
        this.estado = new EstadoManutencao(this);
        System.out.println("Elevador entrou em estado de manutenção!");
    }
}
