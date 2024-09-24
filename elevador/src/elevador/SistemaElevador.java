package elevador;

public class SistemaElevador {
    public static void main(String[] args) {
        Elevador elevador = new Elevador();

        // Comandos para subir e descer
        ComandoSubir subir = new ComandoSubir(elevador, 3); // Requisita subir até o 3º andar
        ComandoDescer descer = new ComandoDescer(elevador, 1); // Requisita descer até o 1º andar

        // Executa os comandos
        subir.executar();
        descer.executar();
    }
}

