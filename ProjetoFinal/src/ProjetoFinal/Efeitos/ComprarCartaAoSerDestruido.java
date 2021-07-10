package ProjetoFinal.Efeitos;

import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class ComprarCartaAoSerDestruido implements Efeito {
	private TipoEfeito tipo;
	
	public ComprarCartaAoSerDestruido() {
		tipo = TipoEfeito.Destruido;
	}
	
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		jogador.comprarCarta();
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
