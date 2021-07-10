package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;

public interface Traco {
	public void atacarInimigo(Seguidores card,Seguidores inimigo);
	public void atacarInimigo(Seguidores card, Jogador player);
	public String verTipo();
}
