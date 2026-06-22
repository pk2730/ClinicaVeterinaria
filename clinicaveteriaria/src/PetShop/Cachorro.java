package PetShop;

public class Cachorro extends Animal implements AtendivelNoEstetica {

	private String raca;
	boolean faminto = true;
	private String tutor;
	private String telefone;

	public Cachorro(String nome, String raca, int idade, String tutor, String telefone) {
		super(nome, idade);
		this.raca = raca;
		this.tutor = tutor;
		this.telefone = telefone;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public boolean Comer() {
		System.out.println("O [nome] comeu!");
		return faminto = false;
	}

	@Override
	public void emitirSom() {
		System.out.println("AU AU!");
	}

	@Override
	public void darBanho() {
		System.out.println("dando banho no cachorro");

	}

	@Override
	public void cortarUnhas() {
		System.out.println("cortando as unhas do cachorro");

	}

	public String exibirDados() {

		return "Nome: " + nome + "\nIdade: " + idade + "\nRaça: " + raca + "\ntutor: " + tutor + "\ntelefone: " + telefone;

	}

}
