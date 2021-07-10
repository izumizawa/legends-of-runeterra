package ProjetoFinal.Efeitos;

import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class DanificarNexus implements Efeito {
	private int vidaDoNexus;
	private TipoEfeito tipo;
	
	public DanificarNexus(int n) {
		vidaDoNexus = n;
		tipo = TipoEfeito.Evocado;
	}
	
	
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		Jogador j = t.verOponente(jogador);
		j.definirVida(vidaDoNexus);
	}
	public TipoEfeito verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}


	@Override
	public void removerEfeitoAplicado(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		
	}

}
