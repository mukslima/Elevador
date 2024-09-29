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

                // Exibir novo menu interativo
                exibirMenuInterativo(elevador, scanner);

                // Se o uso do elevador for excessivo, entra em estado de emergência
                if (usoExcessivo >= 5) {
                    System.out.println("Uso excessivo detectado. Elevador entrando em estado de emergência.");
                    emEmergencia = true;
                    usoExcessivo = 0;
                }
            }
        }
    }

    // Novo método para o menu interativo
    private static void exibirMenuInterativo(Elevador elevador, Scanner scanner) {
        System.out.println("Elevador:");
        System.out.println("Deseja subir ou descer?");
        String escolha = scanner.next().toLowerCase();  // Pega resposta do usuário para subir ou descer

        if (escolha.equals("subir")) {
            System.out.println("Entrando...");
            System.out.println("Escolha o andar desejado (0 a 10): ");
            exibirAndaresDisponiveis(elevador, scanner, true); // Chama método para subir

        } else if (escolha.equals("descer")) {
            System.out.println("Entrando...");
            System.out.println("Escolha o andar desejado (0 a 10): ");
            exibirAndaresDisponiveis(elevador, scanner, false); // Chama método para descer

        } else {
            System.out.println("Escolha inválida. Por favor, digite 'subir' ou 'descer'.");
        }
    }

    // Método para exibir andares e lidar com a lógica de subir ou descer
    private static void exibirAndaresDisponiveis(Elevador elevador, Scanner scanner, boolean subir) {
        for (int i = 0; i <= 10; i++) {
            System.out.print("(" + i + ") ");
        }
        System.out.println(); // Linha em branco

        int andarDesejado = scanner.nextInt();

        if (subir && andarDesejado > elevador.getAndarAtual()) {
            if (andarDesejado <= 10) {
                System.out.println("Porta fechada. Subindo para o andar " + andarDesejado + "...");
                elevador.subir(andarDesejado);
                usoExcessivo++;
                System.out.println("Porta aberta. Você chegou ao andar " + andarDesejado + ".");
            } else {
                System.out.println("Não é possível subir além do 10º andar.");
            }
        } else if (!subir && andarDesejado < elevador.getAndarAtual()) {
            if (andarDesejado >= 0) {
                System.out.println("Porta fechada. Descendo para o andar " + andarDesejado + "...");
                elevador.descer(andarDesejado);
                usoExcessivo++;
                System.out.println("Porta aberta. Você chegou ao andar " + andarDesejado + ".");
            } else {
                System.out.println("Não é possível descer abaixo do térreo (0º andar).");
            }
        } else {
            System.out.println("Operação inválida! Você já está no andar " + elevador.getAndarAtual() + ".");
        }
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
