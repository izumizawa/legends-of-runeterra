package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tracos.Traco;

public class Seguidores extends Carta {
	private Traco traco;

	public Seguidores(String nome,Status carta) {
		super(nome, carta);
		tracos = null;
	}
	
	public void atacarInimigo(Seguidores inimigo) {
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
