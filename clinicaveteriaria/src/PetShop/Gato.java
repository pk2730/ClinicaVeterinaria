package PetShop;

public class Gato extends Animal {

    private boolean arranhaMoveis;

    public Gato(String nome, int idade, boolean arranhaMoveis) {
        super(nome, idade);
        this.arranhaMoveis = arranhaMoveis;
    }

    public boolean isArranhaMoveis() {
        return arranhaMoveis;
    }

    public void setArranhaMoveis(boolean arranhaMoveis) {
        this.arranhaMoveis = arranhaMoveis;
    }

    @Override
    public void emitirSom() {
        System.out.println("MIAU!");
    }
}


