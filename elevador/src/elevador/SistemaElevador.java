package elevador;

import java.util.LinkedList;
import java.util.Queue;

public class SistemaElevador {
    private Queue<ComandoElevador> comandos = new LinkedList<>();

    public void executarComandos() {
        while (!comandos.isEmpty()) {
            ComandoElevador comando = comandos.poll();
            comando.executar();
        }
    }

    public void adicionarComando(ComandoElevador comando) {
        comandos.add(comando);
    }

    public static void main(String[] args) {
        Elevador elevador = new Elevador();
        SistemaElevador sistema = new SistemaElevador();

        // Exemplo de uso, adicionando comandos
        sistema.adicionarComando(new ComandoSubir(elevador, 5));
        sistema.adicionarComando(new ComandoDescer(elevador, 2));
        sistema.adicionarComando(new ComandoDescer(elevador, 0));
        sistema.adicionarComando(new ComandoDescer(elevador, 4));
        sistema.adicionarComando(new ComandoDescer(elevador, 5));
        sistema.adicionarComando(new ComandoDescer(elevador, 7));
        sistema.adicionarComando(new ComandoDescer(elevador, 3));
        sistema.adicionarComando(new ComandoDescer(elevador, 0));
        sistema.adicionarComando(new ComandoEmergencia(elevador));
        sistema.adicionarComando(new ComandoManutencao(elevador));
        sistema.adicionarComando(new ComandoDescer(elevador, 0));
        sistema.adicionarComando(new ComandoDescer(elevador, 2));
        sistema.adicionarComando(new ComandoDescer(elevador, 10));
        sistema.adicionarComando(new ComandoDescer(elevador, 4));
        // Executa os comandos
        sistema.executarComandos();
    }
}
