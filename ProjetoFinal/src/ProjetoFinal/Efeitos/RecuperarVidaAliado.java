package ProjetoFinal.Efeitos;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class RecuperarVidaAliado implements Efeito {

	public RecuperarVidaAliado() {}
	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		boolean respondeu = false;
		int indice = coletarEntrada();
		while(!false) {
			ArrayList<Carta> cartasEmCampo = t.encontraCartasEvocadas(jogador);
			if(indice < cartasEmCampo.size()) {
				Carta card = cartasEmCampo.get(indice);
				int vidaTotal = card.verVidaTotal();
				card.definirVidaAtual(vidaTotal);
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
		System.out.println("Escolha o indice de carta em campo para receber um bonus");
		int resposta = scan.nextInt();
		return resposta;
	}
}
