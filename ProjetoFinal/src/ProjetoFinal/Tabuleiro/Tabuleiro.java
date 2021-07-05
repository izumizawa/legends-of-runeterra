package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;
import java.util.Collections;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Jogador.Jogador;


public class Tabuleiro {
	private Jogador jogador1, jogador2;
	private ArrayList <Carta> cartas_evocadas1, cartas_evocadas2;
	private int rodada;
	private int turno;
	
	public Tabuleiro(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.cartas_evocadas1= new ArrayList<>();
		this.cartas_evocadas2 = new ArrayList<>();
		this.rodada = 0;
		this.turno = 0;
	}
	
	// Adiciona a carta à mesa. Explicitar se é jogador 1 ou 2.
	public void adcCartasEvocadas(Carta carta_abaixada, int jogador) {
		if(jogador == 1) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas1.size(), 1)) {
				this.cartas_evocadas1.add(carta_abaixada);
			}
		}
		
		else if(jogador == 2) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas2.size(), 1)) {
				this.cartas_evocadas2.add(carta_abaixada);
			}
		}
		else {
			return;
		}
	}
	
	//Remove carta da mesa.
	public void rmvCartasEvocadas(Carta carta_removida, int jogador) {
		if(jogador == 1) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas1.size(), 0)) {
				this.cartas_evocadas1.remove(carta_removida);
			}
		}
		
		else if(jogador == 2) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas2.size(), 0)) {
				this.cartas_evocadas2.remove(carta_removida);
			}
		}
		
		else {
			return;
		}
	}
	
	//Embaralha o deck do jogador
	public void embaralhaDeck(ArrayList <Carta> deck) {
		Collections.shuffle(deck);
	}
	
	public void imprimeTabuleiro() {
		
	}
	
	
	//Valida o número de cartas na mesa.
	private boolean checaNumeroCartasEvocadas(int numero_cartas, int tipo) {
		if(tipo == 1) {
			if(numero_cartas < 6) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else if(tipo == 0) {
			if (numero_cartas > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	
}

