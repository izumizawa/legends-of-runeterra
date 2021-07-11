package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class AtaqueDuplo implements Traco {
	private String tipo;
	
	public AtaqueDuplo() {
		tipo="Ataque Duplo";
	}

	@Override
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores card, Seguidores inimigo) {
		// TODO Auto-generated method stub
		atacar(t,j,card,inimigo);
		if(inimigo.verVidaAtual()<=0) {
			card.atacarInimigo(t,t.verOponente(j));
		}
		else {
			atacar(t,j,card,inimigo);
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
		}
		else {
			t.defBloqueioDano(oponente, false);
		}
	}
}
