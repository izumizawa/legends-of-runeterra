package ProjetoFinal.Efeitos;

import ProjetoFinal.Deck.Deck;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class EvocarCartaTipoEspecifico implements Efeito{
	private String tipoCarta;
	private TipoEfeito tipo;
	
	public EvocarCartaTipoEspecifico(String tipoEvocado) {
		tipo = TipoEfeito.DestruirInimigo;
		tipoCarta = tipoEvocado;
	}
	
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		jogador.comprarCartaEspecifica(tipoCarta);
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
