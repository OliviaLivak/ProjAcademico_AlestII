import java.io.*;
import java.util.*;

public class Labirinto {

    public static char[][] leituraArquivo(String caminhoArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
        List<char[]> linhas = new ArrayList<>();
        String linha = br.readLine();
        while ((linha = br.readLine()) != null) {
            linhas.add(linha.replaceAll("\\s+", "").toCharArray());
        }
        br.close();
        return linhas.toArray(new char[0][]);
    }

    public static String nomeDoSer(char ser) {
        return switch (ser) {
            case 'A' -> "Anão";
            case 'B' -> "Bruxa";
            case 'C' -> "Cavaleiro";
            case 'D' -> "Duende";
            case 'E' -> "Elfo";
            case 'F' -> "Feijão";
            default -> "Ser inválido";
        };
    }

    public static List<Map<Character, Integer>> encontrarRegioes(char[][] labirinto) {
        int m = labirinto.length;
        int n = labirinto[0].length;
        boolean[][] visitado = new boolean[m][n];
        List<Map<Character, Integer>> regioes = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitado[i][j]) {
                    Map<Character, Integer> count = new HashMap<>();
                    dfs(labirinto, m, n, i, j, visitado, count);
                    regioes.add(count);
                }
            }
        }

        return regioes;
    }

    private static void dfs(char[][] labirinto, int m, int n, int i, int j, boolean[][] visitado, Map<Character, Integer> seresContagem) {
        visitado[i][j] = true;
        char valor = labirinto[i][j];
        if (Character.isUpperCase(valor)) {
            seresContagem.put(valor, seresContagem.getOrDefault(valor, 0) + 1);
        }

        int[][] direcoes = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int d = 0; d < 4; d++) {
            int nx = i + direcoes[d][0];
            int ny = j + direcoes[d][1];
            if (estaDentro(nx, ny, m, n) && !visitado[nx][ny] && paredes(valor, labirinto[nx][ny], d)) {
                dfs(labirinto, m, n, nx, ny, visitado, seresContagem);
            }
        }
    }

    private static boolean estaDentro(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private static boolean paredes(char valorAtual, char proximoValor, int direcao) {
        String binario1 = String.format("%4s", Integer.toBinaryString(Character.digit(valorAtual, 16))).replace(' ', '0');
        String binario2 = String.format("%4s", Integer.toBinaryString(Character.digit(proximoValor, 16))).replace(' ', '0');
        return binario1.charAt(direcao) == '0' && binario2.charAt((direcao + 2) % 4) == '0';
    }

    public static int contarRegioes(List<Map<Character, Integer>> regioes) {
        return regioes.size();
    }

    public static Map.Entry<String, Integer> obterSerMaisFrequente(List<Map<Character, Integer>> regioes) {
        char serMaisFrequente = '-';
        int maiorFrequencia = 0;

        for (Map<Character, Integer> seres : regioes) {
            for (Map.Entry<Character, Integer> entry : seres.entrySet()) {
                char ser = entry.getKey();
                int count = entry.getValue();

                if (count > maiorFrequencia) {
                    maiorFrequencia = count;
                    serMaisFrequente = ser;
                }
            }
        }

        return new AbstractMap.SimpleEntry<>(nomeDoSer(serMaisFrequente), maiorFrequencia);
    }
}

















