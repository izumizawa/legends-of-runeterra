package ProjetoFinal.Efeitos;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class MelhorarTodosAliados implements Efeito {
	private Status stat;
	
	public MelhorarTodosAliados(int dano, int vida) {
		stat = new Status(0,dano,vida);
	}
	
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		Jogador j = t.verJogador();
		if(j.equals(jogador)) {
			
		}
	}
}
