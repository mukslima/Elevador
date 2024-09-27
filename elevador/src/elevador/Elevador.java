package elevador;

import java.util.ArrayList;
import java.util.List;

class Elevador {
    private Estado estado;
    private int andarAtual;
    private List<Observador> observadores = new ArrayList<>();
    private int contadorCliques = 0; // Para contar os cliques
    private final int ANDAR_MIN = 0;
    private final int ANDAR_MAX = 10;
    private boolean emManutencao = true; // Para evitar loops de manutenção
    

    public Elevador() {
        this.andarAtual = 0; // Começa no térreo
        this.estado = new EstadoParado(this);
    }

    // Método para adicionar observadores
    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Método para notificar todos os observadores quando há mudanças
    private void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.atualizar();
        }
    }

    public void subir(int andarDestino) {
        if (estado instanceof EstadoParado || estado instanceof EstadoDescendo) {
            if (podeSubir(andarDestino)) {
                this.estado = new EstadoSubindo(this);
                System.out.println("O Elevador está subindo para o andar " + andarDestino + ", um momento...");

                while (andarAtual < andarDestino) {
                    andarAtual++;
                    System.out.println("Andar atual: " + andarAtual);
                    notificarObservadores(); // Notifica sempre que muda de andar
                }

                this.estado = new EstadoParado(this);
                System.out.println("O elevador chegou ao andar " + andarDestino);
                fecharPorta();
                resetarContadorCliques(); // Reseta o contador de cliques após a operação
            } else {
                System.out.println("Não é possível subir para o andar " + andarDestino + ". Limite máximo é " + ANDAR_MAX);
            }
        } else {
            System.out.println("O elevador não pode subir, pois está em " + estado.getClass().getSimpleName());
        }
    }

    public void descer(int andarDestino) {
        if (estado instanceof EstadoParado || estado instanceof EstadoSubindo) {
            if (podeDescer(andarDestino)) {
                this.estado = new EstadoDescendo(this);
                System.out.println("O Elevador está descendo para o andar " + andarDestino + ", um momento...");

                while (andarAtual > andarDestino) {
                    andarAtual--;
                    System.out.println("Andar atual: " + andarAtual);
                    notificarObservadores(); // Notifica sempre que muda de andar
                }

                this.estado = new EstadoParado(this);
                System.out.println("O elevador chegou ao andar " + andarDestino);
                fecharPorta();
                resetarContadorCliques(); // Reseta o contador de cliques após a operação
            } else {
                System.out.println("Não é possível descer para o andar " + andarDestino + ". Limite mínimo é " + ANDAR_MIN);
            }
        } else {
            System.out.println("O elevador não pode descer, pois está em " + estado.getClass().getSimpleName());
        }
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    // Método para incrementar o contador de cliques e verificar se aciona emergência
    public void incrementarCliques() {
        contadorCliques++;
        if (contadorCliques >= 20) {
            entrarEmEmergencia();
            resetarContadorCliques(); // Reseta o contador após a emergência ser acionada
        }
    }

    // Método para resetar o contador de cliques
    private void resetarContadorCliques() {
        contadorCliques = 0;
    }

    // Método para aguardar por um período de tempo
    private void aguardarSegundos(int segundos) {
        try {
            for (int i = segundos; i > 0; i--) {
                System.out.print(i + ",");
                Thread.sleep(1000); // Pausa de 1 segundo
            }
            System.out.println(); // Quebra de linha ao fim da contagem
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void entrarEmEmergencia() {
        if (!emManutencao) { // Verifica se não está em manutenção para entrar em emergência
            System.out.println("ALERTA: O elevador entrou em estado de emergência, vai contatar a manutenção...");
            aguardarSegundos(10); // Contagem regressiva de 10 segundos
            entrarEmManutencao(); // Após a emergência, entra em manutenção
        }
    }

    public void entrarEmManutencao() {
        if (!emManutencao) { // Evita múltiplas entradas em manutenção
            emManutencao = true;
            System.out.println("ALERTA: O elevador entrou em manutenção...");
            aguardarSegundos(5); // Contagem regressiva de 5 segundos para simular o estado de manutenção
            System.out.println("Manutenção concluída. Elevador voltando ao normal.");
            this.estado = new EstadoParado(this); // Volta ao estado normal
            emManutencao = false; // Reseta o estado de manutenção
        }
    }

    public void abrirPorta() {
        System.out.println("Porta aberta.");
    }

    public void fecharPorta() {
        System.out.println("Porta fechada.");
    }

    // Método para verificar se pode subir para o andar desejado
    public boolean podeSubir(int andarDesejado) {
        return andarDesejado <= ANDAR_MAX && andarDesejado > andarAtual;
    }

    // Método para verificar se pode descer para o andar desejado
    public boolean podeDescer(int andarDesejado) {
        return andarDesejado >= ANDAR_MIN && andarDesejado < andarAtual;
    }
}
