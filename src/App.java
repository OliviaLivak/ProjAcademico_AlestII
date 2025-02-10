import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App {

    public void executar(String caminhoArquivo) {
        long startTime = System.nanoTime();

        try {

            char[][] labirinto = Labirinto.leituraArquivo(caminhoArquivo);
            List<Map<Character, Integer>> regioes = Labirinto.encontrarRegioes(labirinto);
            int numeroDeRegioes = Labirinto.contarRegioes(regioes);
            System.out.println("Número de regiões isoladas que existem no labirinto: " + numeroDeRegioes);

            Map.Entry<String, Integer> serMaisFrequente = Labirinto.obterSerMaisFrequente(regioes);
            System.out.println("Ser que mais aparece em uma região: " + serMaisFrequente.getKey());
            System.out.println("Quantidade de vezes que o ser aparece em uma região: " + serMaisFrequente.getValue());

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime; 
        System.out.println("Tempo total de execução do algoritmo: " + duration + " nanosegundos");
    }
}








