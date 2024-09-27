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
                    iniciarManutencao();
                    continue;
                }

                if (emManutencao) {
                    System.out.println("Elevador está em manutenção. Aguardando 10 segundos.");
                    iniciarManutencao();
                    continue;
                }

                exibirMenu(elevador);

                int andarDesejado = scanner.nextInt();

                if (andarDesejado > elevador.getAndarAtual() && elevador.podeSubir(andarDesejado)) {
                    System.out.println("Subindo para o andar " + andarDesejado + "...");
                    Comando subir = new ComandoSubir(elevador, andarDesejado);
                    subir.executar();
                    usoExcessivo++;
                } else if (andarDesejado < elevador.getAndarAtual() && elevador.podeDescer(andarDesejado)) {
                    System.out.println("Descendo para o andar " + andarDesejado + "...");
                    Comando descer = new ComandoDescer(elevador, andarDesejado);
                    descer.executar();
                    usoExcessivo++;
                } else {
                    System.out.println("Operação inválida! Andar fora do limite ou já está nesse andar.");
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
        System.out.println("Deseja (1) Subir ou (2) Descer?");
        System.out.print("Pressione o número do andar desejado: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print("(" + i + ") ");
        }
        System.out.println(); // Linha em branco
        System.out.println("Andar atual: " + elevador.getAndarAtual());
    }

    // Temporizador para sair do estado de manutenção ou emergência após 10 segundos
    private static void iniciarManutencao() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manutenção concluída. Elevador voltando ao normal.");
                emManutencao = false;
                emEmergencia = false;
            }
        }, 10000); // 10 segundos de espera
        emManutencao = true; // Durante a espera, estado de manutenção é verdadeiro
    }
}
