package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class Elusivo implements Traco{
	private String tipo;
	
	public Elusivo() {
		tipo = "Elusivo";
	}


	@Override
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores card, Seguidores inimigo) {
		// TODO Auto-generated method stub
		if(inimigo.verTipoTraco().equals(tipo)) {
			atacar(t,j,card,inimigo);
		}
		else {
			Jogador oponente = t.verOponente(j);
			card.atacarInimigo(t,oponente);
		}
		
	}

	public String verTipo() {
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
