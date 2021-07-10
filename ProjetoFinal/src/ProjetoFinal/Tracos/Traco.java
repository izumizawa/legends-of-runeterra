package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public interface Traco {
	public void atacarInimigo(Tabuleiro t,Jogador j,Seguidores card,Seguidores inimigo);
	public String verTipo();
}
