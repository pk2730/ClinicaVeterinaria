package PetShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPetShop extends JFrame {

	private final PetShopRepositorio repositorio = new PetShopRepositorio();

	// ── Campos do formulário ───────────────────────────────
	private final JTextField campNome = new JTextField(10);
	private final JTextField campRaca = new JTextField(10);
	private final JTextField campIdade = new JTextField(5);
	private final JTextField campTutor = new JTextField(10);
	private final JTextField campTelefone = new JTextField(10);

	// ── Área de resultado ──────────────────────────────────
	private final JTextArea areaResultado = new JTextArea(12, 50);

	// ── Botões ─────────────────────────────────────────────
	private final JButton btnCadastrar = new JButton("Cadastrar");
	private final JButton btnBuscar = new JButton("Buscar");
	private final JButton btnAtualizar = new JButton("Atualizar");
	private final JButton btnRemover = new JButton("Remover");

	// ── Construtor ─────────────────────────────────────────
	public TelaPetShop() {
		super("Pet Shop — Gerenciador de Animais");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout(8, 8));

		add(criarPainelFormulario(), BorderLayout.NORTH);
		add(criarAreaResultado(), BorderLayout.CENTER);
		add(criarPainelBotoes(), BorderLayout.SOUTH);

		configurarListeners();

		setSize(900, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// ── Painel Norte: formulário ───────────────────────────
	private JPanel criarPainelFormulario() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));

		painel.setBorder(BorderFactory.createTitledBorder("Dados do Pet"));

		painel.add(new JLabel("Nome:"));
		painel.add(campNome);

		painel.add(new JLabel("Raça:"));
		painel.add(campRaca);

		painel.add(new JLabel("Idade:"));
		painel.add(campIdade);

		painel.add(new JLabel("Tutor:"));
		painel.add(campTutor);

		painel.add(new JLabel("Telefone:"));
		painel.add(campTelefone);

		return painel;
	}

	// ── Centro: área de texto com scroll ──────────────────
	private JScrollPane criarAreaResultado() {
		areaResultado.setEditable(false);
		areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
		areaResultado.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		exibirTexto("Bem-vindo ao sistema do Pet Shop!\n"
				+ "Preencha os campos acima e use os botões para gerenciar os pets.\n");
		return new JScrollPane(areaResultado);
	}

	// ── Painel Sul: botões ─────────────────────────────────
	private JPanel criarPainelBotoes() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));

		painel.add(btnCadastrar);
		painel.add(btnBuscar);
		painel.add(btnAtualizar);
		painel.add(btnRemover);

		return painel;
	}

	// ── ActionListeners ────────────────────────────────────
	private void configurarListeners() {

		// ---- CADASTRAR ----
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = campNome.getText().trim();
				String raca = campRaca.getText().trim();
				String idadeTexto = campIdade.getText().trim();
				String tutor = campTutor.getText().trim();
				String telefone = campTelefone.getText().trim();

				if (nome.isEmpty()) {
					exibirTexto("ERRO: O campo Nome é obrigatório.");
					return;
				}

				if (idadeTexto.isEmpty()) {
					exibirTexto("ERRO: Informe a idade.");
					return;
				}

				int idade;

				try {
					idade = Integer.parseInt(idadeTexto);
				} catch (NumberFormatException ex) {
					exibirTexto("ERRO: Digite uma idade válida.");
					return;
				}

				if (raca.isEmpty()) {
					raca = "Indefinida";
				}

				if (tutor.isEmpty()) {
					tutor = "Não informado";
				}

				if (telefone.isEmpty()) {
					telefone = "Não informado";
				}

				Cachorro novo = new Cachorro(nome, raca, idade, tutor, telefone);

				repositorio.adicionar(novo);
				exibirTexto("Pet cadastrado com sucesso!\n\n" + novo.exibirDados());
				limparCampos();
			}
		});

		btnBuscar.addActionListener(e -> {
			String nome = campNome.getText().trim();

			if (nome.isEmpty()) {
				exibirTexto("Digite um nome para buscar.");
				return;
			}

			Animal encontrado = repositorio.buscar(nome);

			if (encontrado != null) {
				exibirTexto("Pet encontrado:\n\n" + encontrado.exibirDados());
			} else {
				exibirTexto("Pet não encontrado.");
			}
		});

		btnAtualizar.addActionListener(e -> {
			String nome = campNome.getText().trim();
			String raca = campRaca.getText().trim();
			String idadeTexto = campIdade.getText().trim();
			String tutor = campTutor.getText().trim();
			String telefone = campTelefone.getText().trim();

			if (nome.isEmpty()) {
				exibirTexto("Informe o nome do pet.");
				return;
			}

			if (idadeTexto.isEmpty()) {
				exibirTexto("ERRO: Informe a idade.");
				return;
			}

			int idade;

			try {
				idade = Integer.parseInt(idadeTexto);
			} catch (NumberFormatException ex) {
				exibirTexto("ERRO: Digite uma idade válida.");
				return;
			}

			if (raca.isEmpty()) {
				raca = "Indefinida";
			}

			if (tutor.isEmpty()) {
				tutor = "Não informado";
			}

			if (telefone.isEmpty()) {
				telefone = "Não informado";
			}

			Cachorro novo = new Cachorro(nome, raca, idade, tutor, telefone);

			boolean alterou = repositorio.atualizar(nome, novo);

			if (alterou) {
				exibirTexto("Pet atualizado com sucesso!\n\n" + novo.exibirDados());
				limparCampos();
			} else {
				exibirTexto("Pet não encontrado.");
			}
		});

		btnRemover.addActionListener(e -> {
			String nome = campNome.getText().trim();

			if (nome.isEmpty()) {
				exibirTexto("Digite o nome do pet.");
				return;
			}

			boolean removeu = repositorio.remover(nome);

			if (removeu) {
				exibirTexto("Pet removido com sucesso!");
			} else {
				exibirTexto("Pet não encontrado.");
			}
		});
	}

	// ── Métodos auxiliares ─────────────────────────────────
	private void exibirTexto(String texto) {
		areaResultado.setText(texto);
	}

	private void limparCampos() {
		campNome.setText("");
		campRaca.setText("");
		campIdade.setText("");
		campTutor.setText("");
		campTelefone.setText("");

		campNome.requestFocus();
	}
}

