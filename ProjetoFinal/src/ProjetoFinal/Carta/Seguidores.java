package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;

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
	
	private void sofrerDano(int dano) {
		valoresCartaAtual.subtrairVida(dano);
	}
	
	public int verVidaAtual() {
		return valoresCartaAtual.verDefesa();
	}
}
