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
		card.atacarInimigo(t,j,inimigo);
		if(inimigo.verVidaAtual()<=0) {
			card.atacarInimigo(t.verOponente(j));
		}
		else {
			card.atacarInimigo(t,j,inimigo);
		}
	}


	@Override
	public String verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}
}
