package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;

public class Carta {
	Status valoresCarta;

	public Carta(Status carta) {
		valoresCarta = carta;
	}
	
	int verDano() {
		return valoresCarta.verAtaque();
	}
	
}
