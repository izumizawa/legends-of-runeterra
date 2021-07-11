package ProjetoFinal.Carta;

import java.util.ArrayList;

import ProjetoFinal.Efeitos.Efeito;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class Carta {
	private String nome;
	private Status valoresCarta;
	private Status valoresCartaAtual;
	private ArrayList<Efeito> efeitos;
	

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
	public Carta(String nomeCarta,Status carta,Efeito e) {
		valoresCarta = carta;
		nome = nomeCarta;
		ArrayList<Efeito> novosEfeitos = new ArrayList<Efeito>();
		novosEfeitos.add(e);
		efeitos = novosEfeitos;
		valoresCartaAtual = carta;
	}
	
	public Status verStatusAtual() {
		return valoresCartaAtual;
	}
	
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores inimigo) {
		int dano = verDano();
		inimigo.sofrerDano(t,j,dano);
	}
	
	public void atacarInimigo(Tabuleiro t,Jogador player) {
		int dano = verDano();
		player.sofrerDano(t,dano);
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
	
	void definirEfeitos(ArrayList<Efeito> e) {
		efeitos = e;
	}
	
	public ArrayList<Efeito> verEfeitos(){
		return efeitos;
	}
	
	public void cartaEvocada(Tabuleiro t,Jogador j) {
		aplicarEfeitoEvocacao(t,j);
	}
	
	private void aplicarEfeitoEvocacao(Tabuleiro tabuleiro, Jogador jogador) {	//Como passar o tabuleiro como parâmetro? -Rafa
		for(int i=0; i< efeitos.size(); i++) {
			Efeito e = efeitos.get(i);
			if(e.verTipo() == TipoEfeito.Evocado) {
				e.aplicarEfeitos(tabuleiro, jogador);
			}
		}
	}
	
	public void removerEfeitos(Tabuleiro t,Jogador j) {
		for(int i=0; i< efeitos.size(); i++) {
			Efeito e = efeitos.get(i);
				e.removerEfeitoAplicado(t, j);
		}
	}
}
