package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Carta.Feiticos;
import ProjetoFinal.Jogador.Jogador;


public class Tabuleiro {
	private Jogador jogador1, jogador2;
	private ArrayList <Carta> cartas_evocadas1, cartas_evocadas2, cartas_ataque, cartas_defesa;
	private int rodada, turno;

	
	public Tabuleiro(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.cartas_evocadas1= new ArrayList<>();
		this.cartas_evocadas2 = new ArrayList<>();
		this.cartas_ataque = new ArrayList<>();
		this.cartas_defesa = new ArrayList<>();
		this.rodada = -1;
		this.turno = 0;
	}
		
	public Jogador verJogador1() {
		return this.jogador1;
	}
	
	public Jogador verJogador2() {
		return this.jogador2;
	}
	
	public Jogador verOponente(Jogador jogador) {
		if(jogador.equals(jogador1)) {
			return jogador2;
		}
		else {
			return jogador1;
		}
	}
	
	public ArrayList<Carta> verCartasAtaque(){
		return this.cartas_ataque;
	}
	
	public ArrayList<Carta> verCartasDefesa(){
		return this.cartas_defesa;
	}
		
	// Adiciona a carta Ã  mesa. Explicitar se Ã© jogador 1 ou 2.
	public boolean adcCartasEvocadas(Carta carta_abaixada, Jogador jogador) {
		if(jogador.equals(jogador1)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas1.size(), 1)) {
				this.cartas_evocadas1.add(carta_abaixada);
				return true;
			}
		}
		
		else if(jogador.equals(jogador2)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas2.size(), 1)) {
				this.cartas_evocadas2.add(carta_abaixada);
				return true;
			}
		}
		return false;
	}
		
	private void adcCartasCombate(Carta carta, ArrayList<Carta> combate) {
		combate.add(carta);
	}
	
	private void remvCartasCombate(Carta carta, ArrayList<Carta> combate) {
		combate.remove(carta);
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
		int vida1 = this.jogador1.verVida();
		int vida2 = this.jogador2.verVida();

		while((vida1 >= 0) && (vida2 >= 0)) {
			this.rodada ++;
			definirManaInicial();
				
			Jogador jogador_ataq = jogadorAtacante(this.jogador1, this.jogador2);
			Jogador jogador_def = jogadorDefensor(this.jogador1, this.jogador2);
			
			turnoJogada(jogador_ataq);
			turnoAtaque(jogador_ataq);
			
			turnoJogada(jogador_def);
			turnoDefesa(jogador_def);
			
			turnoBatalha();
			
			definirManaFinal(jogador1);
			definirManaFinal(jogador2);
		}	
	}
			
	private void turnoBatalha() {
		
	}
	

	private void turnoDefesa(Jogador defensor) {
		
		System.out.println("Deseja defender? s/n");
		String resposta = leInformacaoStr();
		int numero_carta = 0;
		boolean iteracao = true;
		ArrayList <Carta> cartas_evocadas = encontraCartasEvocadas(defensor);
		
		
		if(resposta.equals("s")) {
			
			System.out.println("Escolha o número das cartas para colocar no campo de batalha!");
			
			while(iteracao) {
				imprimeCartasEvocadas(cartas_evocadas);
				System.out.println("Pressione 0 para concluir");
				numero_carta = (leInformacaoInt() - 1);
				
				if((numero_carta == -1) || (numero_carta > cartas_evocadas.size())) {
					iteracao = false;
				}
				
				else  {
					adcCartasCombate(cartas_evocadas.get(numero_carta), this.cartas_defesa);
					rmvCartasEvocadas(cartas_evocadas.get(numero_carta), defensor);
				}	
			}		
		}		
	}
	
	private void turnoAtaque(Jogador atacante) {
		
		System.out.println("Deseja atacar? s/n");
		String resposta = leInformacaoStr();
		int numero_carta = 0;
		boolean iteracao = true;
		ArrayList <Carta> cartas_evocadas = encontraCartasEvocadas(atacante);
		
		
		if(resposta.equals("s")) {
			
			System.out.println("Escolha o numero das cartas para colocar no campo de batalha!");
			
			while(iteracao) {
				imprimeCartasEvocadas(cartas_evocadas);
				System.out.println("Pressione 0 para concluir");
				numero_carta = (leInformacaoInt() - 1);
				
				if((numero_carta == -1) || (numero_carta > cartas_evocadas.size())) {
					iteracao = false;
				}
				
				else  {
					adcCartasCombate(cartas_evocadas.get(numero_carta), this.cartas_ataque);
					rmvCartasEvocadas(cartas_evocadas.get(numero_carta), atacante);
				}	
			}		
		}
	}
	
	private void turnoJogada(Jogador jogador) {
		jogador.comprarCarta();
		
		boolean imprime_mao = true;													//Decisao para imprimir a mao do jogador
		int carta_escolhida = 0;													//Numero da carta escolhida
		ArrayList <Carta> cartas_evocadas = encontraCartasEvocadas(jogador);		//Mesa do jogador
		
		while(imprime_mao) {			
			System.out.println("Informe o nÃºmero da carta que deseja jogar: ");
			imprimeCartasdaMao(jogador);
			System.out.println("PULAR: Digite 0");
			carta_escolhida = (leInformacaoInt() - 1);
			Carta carta = jogador.verCartasNaMao().get(carta_escolhida);			//Objeto carta escolhido
			
			if(checaNumeroCartasEvocadas(cartas_evocadas.size(), 1)) {
				if((carta_escolhida == -1) || (consomeMana(jogador, carta))) {
					imprime_mao = false;
				}	
			}
			
			else {
				if(!verificaCartaEvocavel(carta)) {									//Se for um feitico, a carta podera ser jogada
					if((carta_escolhida == -1) || (consomeMana(jogador, carta))) {
						imprime_mao = false;
					}	
				}
			}				
		}
				
		if(carta_escolhida != -1) {
			if(verificaCartaEvocavel(jogador.verCartasNaMao().get(carta_escolhida))) { 
				if(adcCartasEvocadas(jogador.verCartasNaMao().get(carta_escolhida), jogador)) {
					//atacante.verCartasNaMao().get(carta_escolhida).aplicarEfeito(this, atacante);		//adiciona efeito
				}
			}
			
			else {
				//atacante.verCartasNaMao().get(carta_escolhida).aplicarEfeito(this, atacante);		//adiciona feitico
			}
		}			
	}
	
	public ArrayList<Carta> encontraCartasEvocadas(Jogador jogador) {	
		if(jogador.equals(this.jogador1)) {
			return cartas_evocadas1;
		}
		else {
			return cartas_evocadas2;
		}		
	}
	
	private boolean consomeMana(Jogador jogador, Carta carta_jogada) {
		int custoMana = carta_jogada.verCustoMana();
		int manaJogador = jogador.verMana();
		int manaFeitico = jogador.verManaFeitico();
		boolean evocavel = verificaCartaEvocavel(carta_jogada);
		if((custoMana <= manaJogador) && evocavel) {
			int mana_atualizada = (manaJogador - custoMana);
			jogador.definirMana(mana_atualizada);
			return true;
		}
		else if( !evocavel && (custoMana <= manaFeitico)) {
			int mana_atualizada = manaFeitico - custoMana;
			jogador.definirManaFeitico(mana_atualizada);
			return true;
		}
		else if(!evocavel && (custoMana <= (manaJogador + manaFeitico))){
			int manaNormalUsada = custoMana - manaFeitico;
			jogador.definirManaFeitico(0);
			int manaNormalAtualizada = manaJogador - manaNormalUsada;
			jogador.definirMana(manaNormalAtualizada);
		}
		return false;
	}
		
	private boolean verificaCartaEvocavel(Carta carta_recebida) {
		if(carta_recebida instanceof Feiticos) {
			return false;
		}
		return true;
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
	 
	private void imprimeCartasEvocadas(ArrayList<Carta> cartas) {
		for(int i = 0; i < cartas.size(); i ++) {
			System.out.println("Carta "+ (i+1));
			System.out.println("Nome: "+cartas.get(i).verNome());
			System.out.println("Custo de Mana: "+cartas.get(i).verCustoMana());
			System.out.println("Vida Total: "+cartas.get(i).verVidaTotal());
			System.out.println("Dano: "+cartas.get(i).verDano());
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
		
	//Valida o numero de cartas na mesa.
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
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int resposta = scan.nextInt();
		return resposta;
	}
	
	private String leInformacaoStr() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String resposta = scan.nextLine();
		return resposta;
	}
	
	private void definirManaInicial() {
		if (rodada < 10) {
			jogador1.definirMana(rodada);
			jogador2.definirMana(rodada);
		} else {
			jogador1.definirMana(10);
			jogador2.definirMana(10);
		}
	}
	
	private void definirManaFinal(Jogador jogador) {
		if (jogador.verMana() > 3)
			jogador.definirManaFeitico(3);
		else if (jogador.verMana() > 0)
			jogador.definirManaFeitico(jogador.verMana());
		jogador.definirMana(0);
	}
}

