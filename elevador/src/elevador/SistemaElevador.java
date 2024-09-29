package elevador;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SistemaElevador {
    private static int usoExcessivo = 0;
    private static boolean emEmergencia = false;
    private static boolean emManutencao = false;

    public static void main(String[] args) {
        Elevador elevador = new Elevador();
        PainelControle painel = new PainelControle(elevador);
        elevador.adicionarObservador(painel);

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                if (emEmergencia) {
                    System.out.println("Elevador entrou em estado de emergência. Contatando manutenção...");
                    iniciarManutencao(elevador);
                    continue;
                }

                if (emManutencao) {
                    System.out.println("Elevador está em manutenção. Aguardando 10 segundos.");
                    iniciarManutencao(elevador);
                    continue;
                }

                exibirMenu(elevador);

                int andarDesejado = scanner.nextInt();

                if (andarDesejado > elevador.getAndarAtual()) {
                    System.out.println("Subindo para o andar " + andarDesejado + "...");
                    elevador.subir(andarDesejado);
                    usoExcessivo++;
                } else if (andarDesejado < elevador.getAndarAtual()) {
                    System.out.println("Descendo para o andar " + andarDesejado + "...");
                    elevador.descer(andarDesejado);
                    usoExcessivo++;
                } else {
                    System.out.println("Operação inválida! Você já está no andar " + andarDesejado + ".");
                }

                // Se o uso do elevador for excessivo, entra em estado de emergência
                if (usoExcessivo >= 5) {
                    System.out.println("Uso excessivo detectado. Elevador entrando em estado de emergência.");
                    emEmergencia = true;
                    usoExcessivo = 0;
                }
            }
        }
    }

    private static void exibirMenu(Elevador elevador) {
        System.out.println("Escolha o andar desejado (0 a 10): ");
        for (int i = 0; i <= 10; i++) {
            System.out.print("(" + i + ") ");
        }
        System.out.println(); // Linha em branco
        System.out.println("Andar atual: " + elevador.getAndarAtual());
    }

    // Temporizador para sair do estado de manutenção ou emergência após 10 segundos
    private static void iniciarManutencao(Elevador elevador) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manutenção concluída. Elevador voltando ao normal.");
                emManutencao = false;
                emEmergencia = false;
                elevador.entrarEmManutencao();
            }
        }, 10000); // 10 segundos de espera
        emManutencao = true; // Durante a espera, estado de manutenção é verdadeiro
    }
}
