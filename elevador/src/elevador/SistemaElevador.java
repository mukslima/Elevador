package elevador;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class SistemaElevador {
    private static int usoExcessivo = 0;
    private static boolean emEmergencia = false;
    private static boolean emManutencao = false;

    public static void main(String[] args) {
        Elevador elevador = new Elevador();
        PainelControle painel = new PainelControle(elevador);
        elevador.adicionarObservador(painel);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (emEmergencia) {
                System.out.println("Elevador está em estado de emergência. Aguardando manutenção.");
                esperarManutencao();
                continue;
            }

            if (emManutencao) {
                System.out.println("Elevador está em manutenção. Aguardando 10 segundos.");
                esperarManutencao();
                continue;
            }

            System.out.println("Deseja (1) Subir, (2) Descer, (3) Emergência, (4) Manutenção?");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.println("Subindo...");
                Comando subir = new ComandoSubir(elevador, elevador.getAndarAtual() + 1);
                subir.executar();
                usoExcessivo++;
            } else if (opcao == 2) {
                System.out.println("Descendo...");
                Comando descer = new ComandoDescer(elevador, elevador.getAndarAtual() - 1);
                descer.executar();
                usoExcessivo++;
            } else if (opcao == 3) {
                System.out.println("Ativando emergência...");
                Comando emergencia = new ComandoEmergencia(elevador);
                emergencia.executar();
            } else if (opcao == 4) {
                System.out.println("Colocando elevador em manutenção...");
                Comando manutencao = new ComandoManutencao(elevador);
                manutencao.executar();
            }

            // Se o uso do elevador for excessivo, entra em estado de emergência
            if (usoExcessivo >= 5) {
                System.out.println("Uso excessivo detectado. Elevador entrando em estado de emergência.");
                emEmergencia = true;
                usoExcessivo = 0;
            }
        }
    }

    // Temporizador para sair do estado de manutenção ou emergência após 10 segundos
    private static void esperarManutencao() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manutenção concluída. Elevador voltando ao normal.");
                emManutencao = false;
                emEmergencia = false;
            }
        }, 10000); // 10 segundos de espera
    }
}
