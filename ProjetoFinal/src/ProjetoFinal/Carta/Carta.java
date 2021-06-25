package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;

public class Carta {
	private String nome;
	private Status valoresCarta;

	public Carta(String nomeCarta,Status carta) {
		valoresCarta = carta;
		nome = nomeCarta;
	}
	
	public void atacarInimigo(Seguidores inimigo) {
		int dano = verDano();
		inimigo.sofrerDano(dano);
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
	
	void definirDano(int dano) {
		valoresCarta.definirDano(dano);
	}
}
