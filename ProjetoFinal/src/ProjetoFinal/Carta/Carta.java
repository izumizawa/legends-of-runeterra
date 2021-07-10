package ProjetoFinal.Carta;

import java.util.ArrayList;

import ProjetoFinal.Efeitos.Efeito;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class Carta {
	private String nome;
	private Status valoresCarta;
	private Status valoresCartaAtual;
	private ArrayList<Efeito> efeitos;
	
	
	public Carta() {}

	public Carta(String nomeCarta,Status carta) {
		valoresCarta = carta;
		nome = nomeCarta;
		efeitos = new ArrayList<Efeito>();
		valoresCartaAtual = carta;

	}
	
	public Carta(String nomeCarta,Status carta,ArrayList<Efeito> e) {
		valoresCarta = carta;
		nome = nomeCarta;
		efeitos = e;
		valoresCartaAtual = carta;
	}
	
	public Status verStatusAtual() {
		return valoresCartaAtual;
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
	public int verDanoAtual() {
		return valoresCartaAtual.verAtaque();
	}
	
	public int verVidaAtual() {
		return valoresCartaAtual.verDefesa();
	}
	
	public int verVidaTotal() {
		return valoresCarta.verDefesa();
	}
	
	public void definirVidaTotal(int vida) {
		valoresCarta.definirVida(vida);
	}
	
	public void definirDanoAtual(int dano) {
		valoresCartaAtual.definirDano(dano);
	}
	
	public void definirVidaAtual(int vida) {
		valoresCarta.definirVida(vida);
	}
	
	public void adicionarEfeito(Efeito e) {
		efeitos.add(e);
	}
	
	public void aplicarEfeito(Tabuleiro tabuleiro, Jogador jogador) {	//Como passar o tabuleiro como parâmetro? -Rafa
		for(int i=0; i< efeitos.size(); i++) {
			Efeito efeito = efeitos.get(i);
			efeito.aplicarEfeitos(tabuleiro, jogador);
		}
	}
}
