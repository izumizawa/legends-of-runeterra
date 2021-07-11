package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class Furia implements Traco{
	private String tipo;
	private Status stat;
	
	public Furia(Status status) {
		stat = status;
	}
	@Override
	public void atacarInimigo(Tabuleiro t,Jogador j,Seguidores card, Seguidores inimigo) {
		// TODO Auto-generated method stub
		atacar(t,j,card,inimigo);
		if(inimigo.verVidaAtual() <= 0) {
			int danoAtual = card.verDanoAtual();
			int vidaAtual = card.verVidaAtual();
			int danoBonus = stat.verAtaque();
			int vidaBonus = stat.verDefesa();
			card.definirDanoAtual(danoAtual + danoBonus);
			card.definirVidaAtual(vidaAtual + vidaBonus);
		}
	}

	@Override
	public String verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}
	
	private void atacar(Tabuleiro t,Jogador j, Seguidores card,Seguidores inimigo) {
		Jogador oponente = t.verOponente(j);
		int dano = card.verDanoAtual();
		boolean bloquear = t.verBloqueioDano(oponente);
		if(!bloquear) {
			inimigo.sofrerDano(t, j,dano);
			int danoDoInimigo = inimigo.verDanoAtual();
			card.sofrerDano(t, j, danoDoInimigo);
		}
		else {
			t.defBloqueioDano(oponente, false);
		}
	}

}
