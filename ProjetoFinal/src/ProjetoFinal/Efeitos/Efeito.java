package ProjetoFinal.Efeitos;

import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public interface Efeito {
	
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador);
	public TipoEfeito verTipo();
	public void removerEfeitoAplicado(Tabuleiro t,Jogador jogador);
}
