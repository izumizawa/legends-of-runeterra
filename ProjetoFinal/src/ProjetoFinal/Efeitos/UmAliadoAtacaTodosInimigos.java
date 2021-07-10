package ProjetoFinal.Efeitos;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class UmAliadoAtacaTodosInimigos implements Efeito {
	private TipoEfeito tipo;
	
	public UmAliadoAtacaTodosInimigos() {
		tipo = TipoEfeito.Evocado;
	}
	
	
	public void aplicarEfeitos(Tabuleiro t,Jogador jogador) {
		boolean respondeu = false;
		int indice = coletarEntrada();
		while(!false) {
			ArrayList<Carta> cartasEmCampo = t.encontraCartasEvocadas(jogador);
			if(indice < cartasEmCampo.size()) {
				Carta card = cartasEmCampo.get(indice);
				batalharComInimigos(t,jogador,card);
				respondeu = true;
			}
			else {
				System.out.println("Indice Invalido, escolha novamente");
				indice = coletarEntrada();
			}
		}
	}
	private int coletarEntrada() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha o indice de carta em campo para ser curada");
		int resposta = scan.nextInt();
		return resposta;
	}
	
	private void batalharComInimigos(Tabuleiro t,Jogador j,Carta card) {
		if(j.equals(t.verJogador1())) {
			ArrayList<Carta> cartasInimigo = t.encontraCartasEvocadas(t.verJogador2());
			batalhas(t,card,j,cartasInimigo);
		}
		else {
			ArrayList<Carta> cartasInimigo = t.encontraCartasEvocadas(t.verJogador2());
			batalhas(t,card,j,cartasInimigo);
		}
	}
	
	private void batalhas(Tabuleiro t,Carta card, Jogador j,ArrayList<Carta> cartasInimigo) {
		for(int i=0;i<cartasInimigo.size();i++) {
			Seguidores s = (Seguidores)cartasInimigo.get(i);
			card.atacarInimigo(t,j,s);
		}
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



	
