package elevador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Elevador {
    private Estado estado;
    private int andarAtual;
    private List<Observador> observadores = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int contadorCliques = 0; // Para contar os cliques

    public Elevador() {
        this.andarAtual = 0; // Começa no térreo
        this.estado = new EstadoParado(this);
    }

    // Método para adicionar observadores1
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
            System.out.println("O elevador não pode subir, pois está em " + estado.getClass().getSimpleName());
        }
    }

    public void descer(int andarDestino) {
        if (estado instanceof EstadoParado || estado instanceof EstadoSubindo) {
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
                System.out.println(i);
                Thread.sleep(1000); // Pausa de 1 segundo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Emergência agora é pública
    public void entrarEmEmergencia() {
        System.out.println("ALERTA: O elevador está em estado de emergência. Aguardando manutenção.");
        this.estado = new EstadoEmergencia(this);
        notificarObservadores();
        aguardarSegundos(5); // Espera 5 segundos para simular o estado de emergência
        entrarEmManutencao(); // Após emergência, entra em manutenção
    }

    // Manutenção agora é pública
    public void entrarEmManutencao() {
        System.out.println("ALERTA: O elevador está em manutenção.");
        this.estado = new EstadoManutencao(this);
        notificarObservadores();
        aguardarSegundos(5); // Espera 5 segundos para simular o estado de manutenção
        System.out.println("Manutenção concluída. Elevador voltando ao normal.");
        this.estado = new EstadoParado(this); // Volta ao estado normal
    }

    public void abrirPorta() {
        System.out.println("Porta aberta.");
    }

    public void fecharPorta() {
        System.out.println("Porta fechada.");
    }
}
