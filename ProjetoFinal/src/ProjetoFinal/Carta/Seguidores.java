package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;

public class Seguidores extends Carta {
	Status valoresCartaAtual;

	public Seguidores(String nome,Status carta) {
		super(nome, carta);
		copiarStatus(carta);
	}
	
	private void copiarStatus(Status carta) {
		int mana = carta.verMana();
		int ataque = carta.verAtaque();
		int defesa = carta.verDefesa();
		valoresCartaAtual = new Status(mana,ataque,defesa);
	}
	
	public void atacarInimigo(Seguidores inimigo) {
		int dano = verDano();
		inimigo.sofrerDano(dano);
	}
	
	//Implementando ataque direto
	public void atacarInimigo(Jogador player) {
		int dano = verDano();
		player.sofrerDano(dano);
	}
	
	public void sofrerDano(int dano) {
		valoresCartaAtual.subtrairVida(dano);
	}
	
	public int verVidaAtual() {
		return valoresCartaAtual.verDefesa();
	}
	
	//Aumenta o dano atual em n
	void definirDanoAtual(int danoBonus) {
		int dano = valoresCartaAtual.verAtaque();
		valoresCartaAtual.definirDano(dano+danoBonus);
	}
	
	void definirVidaAtual(int vidaBonus) {
		int vida = valoresCartaAtual.verDefesa();
		valoresCartaAtual.definirVida(vida + vidaBonus);
	}
}
