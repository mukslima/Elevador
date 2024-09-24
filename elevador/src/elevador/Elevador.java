package elevador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Elevador {
    private Estado estado;
    private int andarAtual;
    private Queue<Integer> filaRequisicoes = new LinkedList<>();
    private List<Observador> observadores = new ArrayList<>();

    public Elevador() {
        this.andarAtual = 0;  // Começa no térreo
        this.estado = new EstadoParado(this);
    }

    public void adicionarObservador(Observador o) {
        observadores.add(o);
    }

    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.atualizar();
        }
    }

    public void requisitarAndar(int andar) {
        filaRequisicoes.add(andar); // Adiciona o andar à fila de requisições
        processarRequisicoes();
    }

    private void processarRequisicoes() {
        while (!filaRequisicoes.isEmpty()) {
            int proximoAndar = filaRequisicoes.poll(); // Pega o próximo andar na fila

            if (proximoAndar > andarAtual) {
                subirAteAndar(proximoAndar);
            } else if (proximoAndar < andarAtual) {
                descerAteAndar(proximoAndar);
            }

            abrirPorta();
        }
    }

    public void subirAteAndar(int andarDestino) {
        while (andarAtual < andarDestino) {
            this.estado = new EstadoSubindo(this);
            estado.executar();
            andarAtual++; // Simula subida
            notificarObservadores();
        }
        fecharPorta();
    }

    public void descerAteAndar(int andarDestino) {
        while (andarAtual > andarDestino) {
            this.estado = new EstadoDescendo(this);
            estado.executar();
            andarAtual--; // Simula descida
            notificarObservadores();
        }
        fecharPorta();
    }

    public void abrirPorta() {
        System.out.println("Porta aberta.");
        notificarObservadores();
    }

    public void fecharPorta() {
        System.out.println("Porta fechada.");
        notificarObservadores();
    }

    public int getAndarAtual() {
        return andarAtual;
    }
}
