package ProjetoFinal.Efeitos;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class MelhorarUmAliado implements Efeito  {
	private Status stat;
	private TipoEfeito tipo;
	private int danoAnterior;
	private int vidaAnterior;
	private int indiceEscolhido;
	
	public MelhorarUmAliado(int dano, int vida) {
		stat = new Status(0,dano,vida);
		tipo=TipoEfeito.Evocado;
		danoAnterior = 0;
		vidaAnterior = 0;
	}

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		boolean respondeu = false;
		int indice = coletarEntrada();
		while(!false) {
			ArrayList<Carta> cartasEmCampo = t.encontraCartasEvocadas(jogador);
			if(indice < cartasEmCampo.size()) {
				Carta card = cartasEmCampo.get(indice);
				danoAnterior = card.verDanoAtual();
				vidaAnterior = card.verVidaAtual();
				indiceEscolhido = indice;
				definirBonusVida(card,stat.verDefesa());
				definirBonusAtaque(card,stat.verAtaque());
				respondeu = true;
			}
			else {
				System.out.println("Indice Invalido, escolha novamente");
				indice = coletarEntrada();
			}
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
	
	
	private int coletarEntrada() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha o indice de carta em campo para receber um bonus");
		int resposta = scan.nextInt();
		return resposta;
	}
	public TipoEfeito verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	@Override
	public void removerEfeitoAplicado(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		Carta card = t.encontraCartasEvocadas(jogador).get(indiceEscolhido);
		card.definirDanoAtual(danoAnterior);
		card.definirVidaAtual(vidaAnterior);
	}
}
