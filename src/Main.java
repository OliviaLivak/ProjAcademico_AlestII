import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o caminho do arquivo: ");
        String caminhoArquivo = scanner.nextLine();

        App app = new App();
        app.executar(caminhoArquivo);
    }
}


