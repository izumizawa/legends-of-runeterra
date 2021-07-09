package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;

public class Seguidores extends Carta {

	public Seguidores(String nome,Status carta) {
		super(nome, carta);
	}
	
	public void atacarInimigo(Seguidores inimigo) {
		int dano = verDano();
		inimigo.sofrerDano(dano);
	}
	
	//Implementando ataque direto
	public void atacarInimigo(Jogador player) {
		int dano = verDano();
		player.sofrerDano(dano);
	}
	
	public void sofrerDano(int dano) {
		int vida = verVidaAtual();
		definirVidaAtual(vida - dano);
	}
	
}
