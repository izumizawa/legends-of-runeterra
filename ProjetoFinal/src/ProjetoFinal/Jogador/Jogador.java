package ProjetoFinal.Jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Deck.Deck;

public class Jogador {

	private String nome;
	private int vida;
	private int mana;
	private Deck deck;
	private List<Carta> cartasNaMao;
	
	public Jogador(String nome, Deck deck) {
		this.nome = nome;
		this.vida = 20;
		this.mana = 0;
		this.deck = deck;
		iniciarCartasNaMao(deck);
	}

	public String verNome() {
		return nome;
	}

	public int verVida() {
		return vida;
	}

	public void definirVida(int vida) {
		this.vida = vida;
	}

	public int verMana() {
		return mana;
	}

	public void definirMana(int mana) {
		this.mana = mana;
	}

	public Deck verDeck() {
		return deck;
	}
	
	private void iniciarCartasNaMao(Deck deck) {
		this.cartasNaMao = new ArrayList<Carta>();
		Random gerador = new Random();
		int i = 0;
		while (i < 4) {
			int cartaAleatoria = gerador.nextInt(40); // 0 a 39
			if (!cartasNaMao.contains(deck.verCartas().get(cartaAleatoria))) {
				Carta carta = deck.verCartas().get(cartaAleatoria);
				this.cartasNaMao.add(carta);
				deck.removerCarta(carta);
				i++;
			}
		}
	}

	public List<Carta> verCartasNaMao() {
		return cartasNaMao;
	}

	public void definirCartasNaMao(List<Carta> cartasNaMao) {
		this.cartasNaMao = cartasNaMao;
	}

}
