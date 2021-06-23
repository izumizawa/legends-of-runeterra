package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;

public class Carta {
	private String nome;
	private Status valoresCarta;

	public Carta(String nomeCarta,Status carta) {
		valoresCarta = carta;
		nome = nomeCarta;
	}
	
	public String verNome() {
		return nome;
	}
	
	int verCustoMana() {
		return valoresCarta.verMana();
	}
	
	int verDano() {
		return valoresCarta.verAtaque();
	}
	
	int verVidaTotal() {
		return valoresCarta.verDefesa();
	}
	
}
