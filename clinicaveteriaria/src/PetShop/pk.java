package PetShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class pk extends JFrame {

		    private String[] palavras = {
		            "JAVA", "COMPUTADOR", "PROGRAMACAO",
		            "DESENVOLVIMENTO", "MACARRAO", "TECLADO"
		    };

		    private String palavraSecreta;
		    private char[] palavraOculta;
		    private int tentativas = 6;

		    private JLabel lblPalavra;
		    private JLabel lblTentativas;
		    private JTextField txtLetra;
		    private JButton btnTentar;

		    public pk () {
		        setTitle("Jogo da Forca");
		        setSize(400, 250);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setLocationRelativeTo(null);
		        setLayout(new FlowLayout());

		        iniciarJogo();

		        lblPalavra = new JLabel(mostrarPalavra());
		        lblPalavra.setFont(new Font("Arial", Font.BOLD, 24));

		        lblTentativas = new JLabel("Tentativas restantes: " + tentativas);

		        txtLetra = new JTextField(5);

		        btnTentar = new JButton("Tentar");

		        btnTentar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                verificarLetra();
		            }
		        });

		        add(lblPalavra);
		        add(lblTentativas);
		        add(txtLetra);
		        add(btnTentar);

		        setVisible(true);
		    }

		    private void iniciarJogo() {
		        Random random = new Random();
		        palavraSecreta = palavras[random.nextInt(palavras.length)];

		        palavraOculta = new char[palavraSecreta.length()];

		        for (int i = 0; i < palavraOculta.length; i++) {
		            palavraOculta[i] = '_';
		        }
		    }

		    private String mostrarPalavra() {
		        StringBuilder sb = new StringBuilder();

		        for (char c : palavraOculta) {
		            sb.append(c).append(' ');
		        }

		        return sb.toString();
		    }

		    private void verificarLetra() {
		        String texto = txtLetra.getText().toUpperCase();

		        if (texto.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "Digite uma letra!");
		            return;
		        }

		        char letra = texto.charAt(0);
		        boolean acertou = false;

		        for (int i = 0; i < palavraSecreta.length(); i++) {
		            if (palavraSecreta.charAt(i) == letra) {
		                palavraOculta[i] = letra;
		                acertou = true;
		            }
		        }

		        if (!acertou) {
		            tentativas--;
		        }

		        lblPalavra.setText(mostrarPalavra());
		        lblTentativas.setText("Tentativas restantes: " + tentativas);

		        txtLetra.setText("");

		        verificarFimDeJogo();
		    }

		    private void verificarFimDeJogo() {
		        if (String.valueOf(palavraOculta).equals(palavraSecreta)) {
		            JOptionPane.showMessageDialog(this, "Você venceu!");
		            System.exit(0);
		        }

		        if (tentativas == 0) {
		            JOptionPane.showMessageDialog(this,
		                    "Você perdeu! A palavra era: " + palavraSecreta);
		            System.exit(0);
		        }
		    }

		    public static void main(String[] args) {
		        SwingUtilities.invokeLater(() -> new pk());
		    }
		

}