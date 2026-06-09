package PetShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CampoMinado extends JFrame {
    private final int linhas;
    private final int colunas;
    private final int minas;
    private JButton[][] botoes;
    private boolean[][] temMina;
    private int[][] vizinhos;
    private boolean[][] revelado;
    private boolean jogoAtivo;
    private int celulasRestantes;

    public CampoMinado(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        this.celulasRestantes = linhas * colunas - minas;

        setTitle("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel(new GridLayout(linhas, colunas));
        botoes = new JButton[linhas][colunas];
        temMina = new boolean[linhas][colunas];
        vizinhos = new int[linhas][colunas];
        revelado = new boolean[linhas][colunas];
        jogoAtivo = true;

        inicializarCampo();

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                final int x = i;
                final int y = j;
                botoes[i][j] = new JButton();
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                botoes[i][j].setMargin(new Insets(0, 0, 0, 0));
                botoes[i][j].addActionListener(e -> revelarCelula(x, y));
                painel.add(botoes[i][j]);
            }
        }

        add(painel, BorderLayout.CENTER);

        JButton reiniciar = new JButton("Reiniciar");
        reiniciar.addActionListener(e -> reiniciarJogo());
        add(reiniciar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarCampo() {
        Random rand = new Random();

        // Coloca minas
        int colocadas = 0;
        while (colocadas < minas) {
            int r = rand.nextInt(linhas);
            int c = rand.nextInt(colunas);
            if (!temMina[r][c]) {
                temMina[r][c] = true;
                colocadas++;
            }
        }

        // Conta vizinhos
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!temMina[i][j]) {
                    vizinhos[i][j] = contarMinasVizinhas(i, j);
                }
            }
        }
    }

    private int contarMinasVizinhas(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx >= 0 && nx < linhas && ny >= 0 && ny < colunas && temMina[nx][ny]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revelarCelula(int x, int y) {
        if (!jogoAtivo || revelado[x][y]) return;

        revelado[x][y] = true;

        if (temMina[x][y]) {
            botoes[x][y].setText("💣");
            botoes[x][y].setBackground(Color.RED);
            fimDeJogo(false);
            return;
        }

        celulasRestantes--;
        botoes[x][y].setEnabled(false);

        if (vizinhos[x][y] > 0) {
            botoes[x][y].setText(String.valueOf(vizinhos[x][y]));
        } else {
            botoes[x][y].setText("");
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nx = x + i;
                    int ny = y + j;
                    if (nx >= 0 && nx < linhas && ny >= 0 && ny < colunas) {
                        revelarCelula(nx, ny);
                    }
                }
            }
        }

        if (celulasRestantes == 0) {
            fimDeJogo(true);
        }
    }

    private void fimDeJogo(boolean venceu) {
        jogoAtivo = false;
        // Revela todas as minas
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (temMina[i][j]) {
                    botoes[i][j].setText("💣");
                }
                botoes[i][j].setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(this, venceu ? "🎉 Você venceu!" : "💥 Você perdeu!");
    }

    private void reiniciarJogo() {
        dispose();
        new CampoMinado(linhas, colunas, minas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String lStr = JOptionPane.showInputDialog("Linhas:");
            String cStr = JOptionPane.showInputDialog("Colunas:");
            String mStr = JOptionPane.showInputDialog("Minas:");

            try {
                int l = Integer.parseInt(lStr);
                int c = Integer.parseInt(cStr);
                int m = Integer.parseInt(mStr);

                if (l <= 0 || c <= 0 || m <= 0 || m >= l * c) {
                    JOptionPane.showMessageDialog(null, "Configuração inválida!");
                    return;
                }

                new CampoMinado(l, c, m);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida!");
            }
        });
    }
}
