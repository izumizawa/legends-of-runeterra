package ProjetoFinal.Jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Deck.Deck;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class Jogador {

	private String nome;
	private int vida;
	private int mana;
	private int mana_feitico;
	private Deck deck;
	private boolean ataque;
	private List<Carta> cartasNaMao;
	
	public Jogador(String nome, Deck deck) {
		this.nome = nome;
		this.vida = 20;
		this.mana = 0;
		this.mana_feitico = 0;
		this.deck = deck;
		this.ataque = false;
	//	iniciarCartasNaMao(deck);
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
	
	//Nathan - Adicionei um metodo para ataque direto.
	public void sofrerDano(Tabuleiro t,int dano) {
		vida = vida - dano;
		if(vida <0) {
			vida = 0;
		}
	}
	
	public int verMana() {
		return mana;
	}

	public void definirMana(int mana) {
		this.mana = mana;
	}

	public int verManaFeitico() {
		return this.mana_feitico;
	}
	
	public void definirManaFeitico(int mana_fei) {
		this.mana_feitico = mana_fei;
	}
	
	public Deck verDeck() {
		return deck;
	}
	
	public boolean verAtaque() {
		return this.ataque;
	}
	
	public void definirAtaque(boolean rodada) {
		this.ataque = rodada;
	}
	
	public void iniciarCartasNaMao() {
		this.cartasNaMao = new ArrayList<Carta>();
		Random gerador = new Random();
		int i = 0;
		while (i < 4) {
			int cartaAleatoria = gerador.nextInt(this.deck.verCartas().size()); // De acordo com o número de cartas do Deck
			if (!cartasNaMao.contains(this.deck.verCartas().get(cartaAleatoria))) {
				Carta carta = this.deck.verCartas().get(cartaAleatoria);
				this.cartasNaMao.add(carta);
				this.deck.removerCarta(carta);
				i++;
			}
		}
	}

	
	public void comprarCarta() {
		Carta carta_pega = this.deck.verCartas().get(0);
		this.cartasNaMao.add(carta_pega);
		deck.removerCarta(carta_pega);
	}
	
	public void comprarCartaEspecifica(String nome) {
		Carta carta = deck.pegarCartaEspecifica(nome);
		deck.removerCarta(carta);
		cartasNaMao.add(carta);
	}
	
	public void reiniciarCartasNaMao(int quantidade) {
		//Método para redefinir as cartas na mão do jogador
		//Cria uma nova lista de cartas, copia as cartasNaMao para a lista
		//Pega as novas cartas
		//Relocar as cartas tiradas de volta no baralho
		ArrayList<Carta> novasCartasNaMao = new ArrayList<Carta>();
		Random gerador = new Random();
		int i = 0;
		
		while(i < quantidade) {
			int cartaAleatoria = gerador.nextInt(this.deck.verCartas().size());
			Carta carta = deck.verCartas().get(cartaAleatoria);
			if(!novasCartasNaMao.contains(carta)) {
				novasCartasNaMao.add(carta);
				deck.removerCarta(carta);
				i++;
			}
		}
		
		Collections.shuffle(cartasNaMao);
		
		for(i = 0; i < quantidade; i++) {
			deck.adicionarCarta(cartasNaMao.get(i));
			cartasNaMao.set(i, novasCartasNaMao.get(i));
		}
	}
	
	public void removerCartadaMao(Carta carta_removida) {
		this.cartasNaMao.remove(carta_removida);
	}
	
	
	public List<Carta> verCartasNaMao() {
		return cartasNaMao;
	}

	public void definirCartasNaMao(List<Carta> cartasNaMao) {
		this.cartasNaMao = cartasNaMao;
	}

}
