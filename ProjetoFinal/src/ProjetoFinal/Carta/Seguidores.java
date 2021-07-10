package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;
import ProjetoFinal.Tracos.Traco;

public class Seguidores extends Carta {
	private Traco traco;

	public Seguidores(String nome,Status carta) {
		super(nome, carta);
		traco = null;
	}
	
	@Override
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores inimigo) {
		int dano = verDanoAtual();
		inimigo.sofrerDano(dano);
	}
	
	//Implementando ataque direto
	public void atacarInimigo(Jogador player) {
		int dano = verDanoAtual();
		player.sofrerDano(dano);
	}
	
	public void sofrerDano(int dano) {
		int vida = verVidaAtual();
		definirVidaAtual(vida - dano);
	}

	public String verTraco() {
		// TODO Auto-generated method stub
		return traco.verTipo();
	}
	
	public void adicionarTraco(Traco t) {
		traco = t;
	}
	
}
