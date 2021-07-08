package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Carta.Feiticos;
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
	public void adcCartasEvocadas(Carta carta_abaixada, Jogador jogador) {
		if(jogador.equals(jogador1)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas1.size(), 1)) {
				this.cartas_evocadas1.add(carta_abaixada);
			}
		}
		
		else if(jogador.equals(jogador2)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas2.size(), 1)) {
				this.cartas_evocadas2.add(carta_abaixada);
			}
		}
		else {
			return;
		}
	}
	
	//Remove carta da mesa.
	public void rmvCartasEvocadas(Carta carta_removida, Jogador jogador) {
		if(jogador.equals(jogador1)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas1.size(), 0)) {
				this.cartas_evocadas1.remove(carta_removida);
			}
		}
		
		else if(jogador.equals(jogador2)) {
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
	
	public void IniciaJogo() {
		jogador1.iniciarCartasNaMao();
		jogador2.iniciarCartasNaMao();
		
		System.out.println("Cartas de "+this.jogador1.verNome()+":");
		imprimeCartasdaMao(jogador1);
		
		System.out.println("Cartas de "+this.jogador2.verNome()+":");
		imprimeCartasdaMao(jogador2);
		
		reiniciaMao(jogador1);
		reiniciaMao(jogador2);
		
	}

	public void RodadasJogo() {
		this.rodada ++;
		
		Jogador jogador_ataq = jogadorAtacante(this.jogador1, this.jogador2);
		Jogador jogador_def = jogadorDefensor(this.jogador1, this.jogador2);
		
		
		
	}
	
	
	
	public void imprimeTabuleiro() {
		
	}
	
	
	private void turnoAtacante(Jogador atacante) {
		atacante.comprarCarta();
		
		System.out.println("Informe o número da carta que deseja jogar: ");
		
		imprimeCartasdaMao(atacante);
		System.out.println("PULAR: Insira qualquer outro número");
		
		int carta_escolhida = (leInformacaoInt() - 1);
		
		if((carta_escolhida >= atacante.verCartasNaMao().size()) || carta_escolhida == 0) {
			return;
		}
		
		else {		
			if(verificaCartaEvocavel(atacante.verCartasNaMao().get(carta_escolhida))) {
				adcCartasEvocadas(atacante.verCartasNaMao().get(carta_escolhida), atacante); 
			}
		//	atacante.verCartasNaMao().get(carta_escolhida).aplicarEfeito(this, atacante);	//Aplica efeito da carta
		}
		
		
	}
	
	
	private boolean consomeMana(Jogador jogador, Carta carta_jogada) {
			
		if((carta_jogada.verCustoMana() <= jogador.verMana()) && (verificaCartaEvocavel(carta_jogada))) {
			int mana_atualizada = (jogador.verMana() - carta_jogada.verCustoMana());
			jogador.definirMana(mana_atualizada);
			return true;
		}
		
		else if(!(verificaCartaEvocavel(carta_jogada)) && (carta_jogada.verCustoMana() <= (jogador.verMana() + jogador.verManaFeitico()))) {
			
		}
		
		else {
			return false;
		}
	}
	
	
	private boolean verificaCartaEvocavel(Carta carta_recebida) {
		if(carta_recebida instanceof Feiticos) {
			return false;
		}
		else return true;
	}
	
	private Jogador jogadorAtacante(Jogador jogador_a, Jogador jogador_b) {	
		if(jogador_a.verAtaque() == true) {
			jogador_b.definirAtaque(true);
			jogador_a.definirAtaque(false);
			return jogador_b;
		}
		else {
			jogador_b.definirAtaque(false);
			jogador_a.definirAtaque(true);
			return jogador_a;
		}
		
	}
	
	private Jogador jogadorDefensor(Jogador jogador_a, Jogador jogador_b) {
		Jogador jogador_ataq = jogadorAtacante(jogador_a, jogador_b);
		
		if(jogador_a.equals(jogador_ataq)) {
			return jogador_b;
		}
		else {
			return jogador_a;
		}
	}
	
	private void imprimeCartasdaMao(Jogador jogador) {
		
		for(int i = 0; i < jogador.verCartasNaMao().size(); i ++) {
			System.out.println("Carta "+ (i+1));
			System.out.println("Nome: "+jogador.verCartasNaMao().get(i).verNome());
			System.out.println("Custo de Mana: "+jogador.verCartasNaMao().get(i).verCustoMana());
			System.out.println("Vida Total: "+jogador.verCartasNaMao().get(i).verVidaTotal());
			System.out.println("Dano: "+jogador.verCartasNaMao().get(i).verDano());
			System.out.println("");
		}
	}
	
	private void reiniciaMao(Jogador jogador) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println(jogador.verNome()+" deseja fazer pegar novas cartas? s/n");
		String resposta = scan.next();
		
		
		if(resposta.equals("s")) {
			System.out.println("Quantas cartas deseja trocar?");
			int quant = scan.nextInt();
			jogador.reiniciarCartasNaMao(quant);
		}
		
		//scan.close();
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
	
	private int leInformacaoInt() {
		Scanner scan = new Scanner(System.in);
		int resposta = scan.nextInt();
		return resposta;
	}
	
	private String leInformacaoStr() {
		Scanner scan = new Scanner(System.in);
		String resposta = scan.nextLine();
		return resposta;
	}
	
}

