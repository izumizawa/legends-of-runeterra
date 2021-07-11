package ProjetoFinal.Efeitos;

import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class BloquearUmAtaque implements Efeito {
	private TipoEfeito tipo;

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		t.defBloqueioDano(jogador, true);
	}

	@Override
	public TipoEfeito verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	@Override
	public void removerEfeitoAplicado(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		t.defBloqueioDano(jogador, false);
	}

}
