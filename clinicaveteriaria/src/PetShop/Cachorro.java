package PetShop;

public class Cachorro extends Animal implements AtendivelNoEstetica {

    private String raca;
  boolean faminto = true;
    public Cachorro(String nome, String raca, int idade, Cliente dono) {
        super(nome, idade);
        this.raca = raca;
        this.dono = dono;
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
}