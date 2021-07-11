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
	
	public Seguidores(String nome, Status carta, Efeito e) {
		super(nome, carta);
		this.adicionarEfeito(e);
		
	}
	
	public Seguidores(String nome, Status carta, Traco t, Efeito e) {
		super(nome, carta);
		this.adicionarEfeito(e);
		traco = t;		
	}
	
	@Override
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores inimigo) {
		Jogador oponente = t.verOponente(j);
		if(traco == null) {
			int dano = verDanoAtual();
			boolean bloquear = t.verBloqueioDano(oponente);
			if(!bloquear) {
				inimigo.sofrerDano(t, j,dano);
			}
			else {
				t.defBloqueioDano(oponente, false);
			}
		}
		else {
			traco.atacarInimigo(t, j, this, inimigo);
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

	public String verTipoTraco() {
		// TODO Auto-generated method stub
		return traco.verTipo();
	}
	public Traco verTraco() {
		return traco;
	}
	
	public void adicionarTraco(Traco t) {
		traco = t;
	}
	
}
