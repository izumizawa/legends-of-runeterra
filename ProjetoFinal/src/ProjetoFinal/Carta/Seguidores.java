package ProjetoFinal.Carta;

import ProjetoFinal.Efeitos.Efeito;
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
	
	public Seguidores(String nome,Status carta,Traco t) {
		super(nome, carta);
		traco = t;
	}
	
	@Override
	public void adicionarEfeito(Efeito e) {
		definirEfeitos(null);
	}
	
	@Override
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores inimigo) {
		if(traco == null) {
			int dano = verDanoAtual();
			inimigo.sofrerDano(t,j,dano);
		}
		else {
			traco.atacarInimigo(t, j, inimigo, inimigo);
		}
		
	}
	
	//Implementando ataque direto
	public void atacarInimigo(Tabuleiro t,Jogador player) {
		int dano = verDanoAtual();
		player.sofrerDano(t,dano);
	}
	
	public void sofrerDano(Tabuleiro t,Jogador j,int dano) {
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
