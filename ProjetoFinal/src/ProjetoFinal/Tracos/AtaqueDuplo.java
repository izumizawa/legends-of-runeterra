package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;

public class AtaqueDuplo implements Traco {
	private String tipo;
	
	public AtaqueDuplo() {
		tipo="Ataque Duplo";
	}

	@Override
	public void atacarInimigo(Seguidores card, Seguidores inimigo) {
		// TODO Auto-generated method stub
		card.atacarInimigo(inimigo);
		card.atacarInimigo(inimigo);
	}

	@Override
	public void atacarInimigo(Seguidores card, Jogador player) {
		// TODO Auto-generated method stub
		card.atacarInimigo(player);
	}

	@Override
	public String verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}
}
