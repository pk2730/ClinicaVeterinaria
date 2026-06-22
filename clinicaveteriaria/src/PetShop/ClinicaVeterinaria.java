package PetShop;

public class ClinicaVeterinaria {

    public static void main(String[] args) {
 Cliente kaua = new Cliente();
 kaua.nome = "pedro";
 kaua.numero = "123456789";
 
 
        Animal[] filaAtendimento = new Animal[2];

        filaAtendimento[0] = new Cachorro("Rex", "Pastor Alemão", 5, kaua);
        filaAtendimento[1] = new Gato("Mingau", 2, true);
        
        for (Animal animal : filaAtendimento) {
            animal.exibirDados();
            animal.emitirSom();
            
        }
         AtendivelNoEstetica[] filaEstetica = new  AtendivelNoEstetica[1];
         filaEstetica[0] = new Cachorro(null, null, 0, kaua);
         for (AtendivelNoEstetica animal : filaEstetica) {
        	 animal.darBanho();
             animal.cortarUnhas();
         }
        System.out.println("\nTotal de animais: " + Animal.totalAnimais);
    }
}