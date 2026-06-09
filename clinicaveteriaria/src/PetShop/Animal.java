package PetShop;

public class Animal {
	
	    protected String nome;
	    protected int idade;
	    protected String dono;

	    public static int totalAnimais = 0;

	    public Animal(String nome, int idade) {
	        this.nome = nome;
	        this.idade = idade;
	        totalAnimais++;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public int getIdade() {
	        return idade;
	    }

	    public void setIdade(int idade) {
	        this.idade = idade;
	    }

	    public String getDono() {
	        return dono;
	    }

	    public void setDono(String dono) {
	        this.dono = dono;
	    }

	    public void emitirSom() {
	        System.out.println("O animal emite um som");
	    }
	}

