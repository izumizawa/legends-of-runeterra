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
		if(inimigo.verTraco().equals(tipo)) {
			card.atacarInimigo(t,j,inimigo);
		}
		else {
			Jogador oponente = t.verOponente(j);
			card.atacarInimigo(t,oponente);
			
		}
		
	}

	public String verTipo() {
		return tipo;
	}
	
}
