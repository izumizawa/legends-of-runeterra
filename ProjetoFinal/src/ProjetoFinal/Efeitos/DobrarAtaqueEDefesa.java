package ProjetoFinal.Efeitos;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class DobrarAtaqueEDefesa implements Efeito{

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		int indice = coletarEntrada();
		ArrayList<Carta> cartasNaMao = t.encontraCartasEvocadas(jogador);
		while(indice >= cartasNaMao.size()) {
			System.out.println("Indice inválido");
			indice = coletarEntrada();
		}
		
		Carta card = cartasNaMao.get(indice);
		int ataqueAtual = card.verDanoAtual();
		int vidaAtual = card.verVidaAtual();
		int novoDano = 2*ataqueAtual;
		int novaVida = 2*vidaAtual;
		card.definirDanoAtual(novoDano);
		card.definirVidaAtual(novaVida);
	}
	
	private int coletarEntrada() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha o indice de carta em campo para receber um bonus");
		int resposta = scan.nextInt();
		return resposta;
	}

}
