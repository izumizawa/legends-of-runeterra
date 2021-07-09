package ProjetoFinal.Efeitos;

import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class DanificarNexus implements Efeito {
	private int vidaDoNexus;
	
	public DanificarNexus(int n) {
		vidaDoNexus = n;
	}
	
	
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		Jogador j = t.verOponente(jogador);
		j.definirVida(vidaDoNexus);
	}

}
