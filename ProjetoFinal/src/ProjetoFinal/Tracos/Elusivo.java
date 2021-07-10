package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;

public class Elusivo implements Traco{
	private String tipo;
	
	public Elusivo() {
		tipo = "Elusivo";
	}


	@Override
	public void atacarInimigo(Seguidores card, Seguidores inimigo) {
		// TODO Auto-generated method stub
		if(inimigo.verTraco().equals(tipo)) {
			card.atacarInimigo(inimigo);
		}
		else {
			atacarInimigo(card,card.verJogador());
		}
		
	}

	@Override
	public void atacarInimigo(Seguidores card, Jogador player) {
		// TODO Auto-generated method stub
		
	}
	
	public String verTipo() {
		return tipo;
	}
	
}
