package PetShop;

public class Cliente {
 protected  String nome;
  protected String numero;
   void exibirInfo() {
	   System.out.println("nome : " + nome);
	   System.out.println("numero : " + numero);
	   
   }
  public String toString() {
	  return this.nome;
  }
}
