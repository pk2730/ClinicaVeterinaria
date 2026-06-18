package PetShop;

public abstract class Animal {
	
	    protected String nome;
	    protected int idade;
	    public  Cliente dono;
   protected  boolean faminto;
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

	   

	    public abstract void emitirSom();
	      
	}

