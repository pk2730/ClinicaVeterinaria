package PetShop;

public class Cliente {
   String nome;
   String numero;
   void exibirInfo() {
	   System.out.println("nome : " + nome);
	   System.out.println("numero : " + numero);
	   
   }
  public String toString() {
	  return this.nome;
  }
}
