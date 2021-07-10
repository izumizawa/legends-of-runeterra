package ProjetoFinal.Efeitos;

import java.util.ArrayList;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class MelhorarTodosAliados implements Efeito {
	private Status stat;
	private TipoEfeito tipo;
	
	public MelhorarTodosAliados(int dano, int vida) {
		tipo = TipoEfeito.Evocado;
		stat = new Status(0,dano,vida);
	}
	
	
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		Jogador j = t.verJogador1();
		if(j.equals(jogador)) {
			iteraCartasJogadorAplicandoEfeito(t,jogador);
		}
		else {
			iteraCartasJogadorAplicandoEfeito(t,t.verJogador2());
		}
	}
	
	
	
	private void iteraCartasJogadorAplicandoEfeito(Tabuleiro t,Jogador j) {
		ArrayList<Carta> cartasNoCampo = t.encontraCartasEvocadas(j);
		for(int i = 0; i<cartasNoCampo.size();i++) {
			Carta card = cartasNoCampo.get(i);
			definirBonusVida(card,stat.verDefesa());
			definirBonusAtaque(card,stat.verAtaque());
		}
	}
	
	
	private void definirBonusVida(Carta card,int vidaBonus) {
		int vida = card.verVidaTotal();
		int vidaAtualizada = vida + stat.verDefesa();
		card.definirVidaAtual(vidaAtualizada);
	}
	private void definirBonusAtaque(Carta card,int danoBonus) {
		int dano = card.verDano();
		int danoAtualizado = dano + stat.verAtaque();
		card.definirDanoAtual(danoAtualizado);
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
