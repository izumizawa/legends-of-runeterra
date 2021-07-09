package ProjetoFinal.Efeitos;

import java.util.Scanner;

import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class CombateImediato implements Efeito {

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		int indiceAliado = coletarAliado();
		while(indiceAliado >= t.encontraCartasEvocadas(jogador).size()) {
			indiceAliado = coletarAliado();
		}
		int indiceInimigo = coletarInimigo();
		while(indiceInimigo >= t.encontraCartasEvocadas(jogador).size()) {
			indiceInimigo = coletarInimigo();
		}
		
	}
	
	
	
	
	private int coletarAliado() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha um aliado para entrar em combate");
		int resposta = scan.nextInt();
		return resposta;
	}
	private int coletarInimigo() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha um inimigo para entrar em combate");
		int resposta = scan.nextInt();
		return resposta;
	}
	
}
