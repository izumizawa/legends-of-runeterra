package ProjetoFinal.Efeitos;

import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class ComprarCartaAoSerDestruido implements Efeito {

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		jogador.comprarCarta();
	}

}
