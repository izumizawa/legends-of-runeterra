package ProjetoFinal.Efeitos;

import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class ZerarAtaque implements Efeito {

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		int indice = coletarEntrada();
		while(indice >= t.encontraCartasEvocadas(jogador).size()) {
			indice = coletarEntrada();
		}
		Carta card = t.encontraCartasEvocadas(jogador).get(indice);
		card.definirDanoAtual(0);
	}
	private int coletarEntrada() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha o indice de carta em campo para receber um bonus");
		int resposta = scan.nextInt();
		return resposta;
	}
}
