package ProjetoFinal.Carta;

import java.util.ArrayList;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class Carta {
	private String nome;
	private Status valoresCarta;
	private ArrayList<Integer> efeitos;

	public Carta(String nomeCarta,Status carta) {
		valoresCarta = carta;
		nome = nomeCarta;
		efeitos = new ArrayList<Integer>();
	}
	
	public void atacarInimigo(Seguidores inimigo) {
		int dano = verDano();
		inimigo.sofrerDano(dano);
	}
	
	public String verNome() {
		return nome;
	}
	
	public int verCustoMana() {
		return valoresCarta.verMana();
	}
	
	public int verDano() {
		return valoresCarta.verAtaque();
	}
	
	public int verVidaTotal() {
		return valoresCarta.verDefesa();
	}
	
	public void definirDano(int dano) {
		valoresCarta.definirDano(dano);
	}
	
	public void aplicarEfeito(Tabuleiro tabuleiro, int jogador) {
		for(int i=0; i< efeitos.size(); i++) {
			int efeito = efeitos.get(i);
			
		}
	}
}
