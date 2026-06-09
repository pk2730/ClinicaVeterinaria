package PetShop;

public class ClinicaVeterinaria {

    public static void main(String[] args) {

        Animal[] filaAtendimento = new Animal[2];

        filaAtendimento[0] = new Cachorro("Rex", "Pastor Alemão", 5);
        filaAtendimento[1] = new Gato("Mingau", 2, true);

        for (Animal animal : filaAtendimento) {
            System.out.print(animal.getNome() + ": ");
            animal.emitirSom();
        }

        System.out.println("\nTotal de animais: " + Animal.totalAnimais);
    }
}