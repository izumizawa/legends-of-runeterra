package ProjetoFinal.Efeitos;

import ProjetoFinal.Deck.Deck;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class EvocarCartaTipoEspecifico implements Efeito{
	private String tipo;
	
	public EvocarCartaTipoEspecifico(String nome) {
		tipo = nome;
	}
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		jogador.comprarCartaEspecifica(tipo);
	}

}
