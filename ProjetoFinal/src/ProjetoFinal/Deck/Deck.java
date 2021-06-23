package ProjetoFinal.Deck;

import java.util.ArrayList;
import java.util.List;

import ProjetoFinal.Carta.Carta;

public class Deck {

	private List<Carta> cartas;

	public Deck() {
		this.cartas = new ArrayList<Carta>(40);
	}

	public List<Carta> verCartas() {
		return cartas;
	}
	
	public void definirCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public void adicionarCarta(Carta carta) {
		this.cartas.add(carta);
	}

	public void removerCarta(Carta carta) {
		this.cartas.remove(carta);
	}

	public Carta pegarCartaDeCima() {
		Carta carta = this.cartas.get(0);
		this.cartas.remove(0);
		return carta;
	}

}