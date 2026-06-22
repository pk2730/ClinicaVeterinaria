package PetShop;

import java.util.ArrayList;

public class PetShopRepositorio {
	private final ArrayList<Animal> animais = new ArrayList<>();

	/** Adiciona um animal à lista. */
	public void adicionar(Animal a) {
		animais.add(a);
	}

	// Busca um animal pelo nome (case-insensitive).
	public Animal buscar(String nome) {
		for (Animal pet : animais) {
			if (pet.getNome().equalsIgnoreCase(nome)) {
				return pet;
			}
		}
		return null;
	}

	public boolean atualizar(String nome, Cachorro novoPet) {
		for (int i = 0; i < animais.size(); i++) {
			Animal pet = animais.get(i);

			if (pet.getNome().equalsIgnoreCase(nome)) {
				animais.set(i, novoPet);
				return true;
			}       // oiiiiii
		}
		return false;
	}

	public boolean remover(String nome) {
		Animal pet = buscar(nome);

		if (pet != null) {
			animais.remove(pet);
			return true;
		}

		return false;
	}

	public ArrayList<Animal> listarTodos() {
		return animais;
	}

	/** Quantidade de animais cadastrados no repositório. */
	public int quantidade() {
		return 0;
	}
}
