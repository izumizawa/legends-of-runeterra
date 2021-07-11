package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Carta.Feiticos;
import ProjetoFinal.Jogador.Jogador;


public class Tabuleiro {
	private Jogador jogador1, jogador2;
	private ArrayList <Carta> cartas_evocadas1, cartas_evocadas2, cartas_ataque, cartas_defesa;
	protected int rodada;
	private boolean bloquear_dano1, bloquear_dano2;
	
	public Tabuleiro(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.cartas_evocadas1= new ArrayList<>();
		this.cartas_evocadas2 = new ArrayList<>();
		this.cartas_ataque = new ArrayList<>();
		this.cartas_defesa = new ArrayList<>();
		this.rodada = 0;
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
	
	public ArrayList<Carta> verCartasEvocadas1(){
		return this.cartas_evocadas1;
	}
	
	public ArrayList<Carta> verCartasEvocadas2(){
		return this.cartas_evocadas2;
	}		
	
	public boolean verBloqueioDano(Jogador jogador) {
		if(this.jogador1.equals(jogador)) {
			return this.bloquear_dano1;
		}
		return this.bloquear_dano2;
	}
	
	
	public void defBloqueioDano(Jogador jogador, boolean valor) {
		if(this.jogador1.equals(jogador)) {
			this.bloquear_dano1 = valor;
			
		}
		else {
			this.bloquear_dano2 = valor;
		}
	}
	
	public int verRodada() {
		return this.rodada;
	}
	
	
	// Adiciona a carta ÃƒÂ  mesa. Explicitar se ÃƒÂ© jogador 1 ou 2.
	public void adcCartasEvocadas(Carta carta_abaixada, Jogador jogador) {
		if(jogador.equals(jogador1)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas1.size(), 1)) {
				this.cartas_evocadas1.add(carta_abaixada);
				return; 
			}
		}
		
		else if(jogador.equals(jogador2)) {
			if(checaNumeroCartasEvocadas(this.cartas_evocadas2.size(), 1)) {
				this.cartas_evocadas2.add(carta_abaixada);
				return;
			}
		}
		return;
	}
		
	protected void adcCartasCombate(Carta carta, ArrayList<Carta> combate) {
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
	
	public void iniciaJogo() {
		jogador1.iniciarCartasNaMao();
		jogador2.iniciarCartasNaMao();
		
		System.out.println("**** Cartas de "+this.jogador1.verNome()+":");
		imprimeCartasdaMao(jogador1);
		
		System.out.println("**** Cartas de "+this.jogador2.verNome()+":");
		imprimeCartasdaMao(jogador2);
		
		reiniciaMao(jogador1);
		System.out.println("**** Cartas finais de "+this.jogador1.verNome()+":");
		imprimeCartasdaMao(jogador1);
		reiniciaMao(jogador2);
		System.out.println("**** Cartas finais de "+this.jogador2.verNome()+":");
		imprimeCartasdaMao(jogador2);
		
	}

	public void rodadasJogo() {
		int vida1 = this.jogador1.verVida();
		int vida2 = this.jogador2.verVida();

		while((vida1 >= 0) && (vida2 >= 0)) {
			this.rodada ++;
			definirManaInicial();
				
			Jogador jogador_ataq = jogadorAtacante(this.jogador1, this.jogador2);
			Jogador jogador_def = jogadorDefensor(jogador_ataq);
			imprimeCampo();
			
			turnoJogada(jogador_ataq);
			turnoAtaque(jogador_ataq);
			
			turnoJogada(jogador_def);
			turnoDefesa(jogador_def);
			
			turnoBatalha();
			
			definirManaFinal(jogador_ataq);
			definirManaFinal(jogador_def);
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
			
			System.out.println("Escolha o nÃºmero das cartas para colocar no campo de batalha!");
			
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
		int carta_escolhida = 0;													//Numero da ca rta escolhida
		ArrayList <Carta> cartas_evocadas = encontraCartasEvocadas(jogador);		//Mesa do jogador
		
		while(imprime_mao) {
			imprimeJogador(jogador);
			System.out.println("Informe o numero da carta que deseja jogar: ");
			imprimeCartasdaMao(jogador);
			System.out.println("PULAR: Digite 0");
			carta_escolhida = (leInformacaoInt() - 1);
					
			if(carta_escolhida == -1) {
				imprime_mao = false;
			}
			
			else if(checaNumeroCartasEvocadas(cartas_evocadas.size(), 1) && (verificaCartaEvocavel(jogador.verCartasNaMao().get(carta_escolhida)))) {
				Carta carta = jogador.verCartasNaMao().get(carta_escolhida);
				if(consomeMana(jogador, carta)) {
					adcCartasEvocadas(carta, jogador);
					carta.cartaEvocada(this, jogador);
					jogador.removerCartadaMao(carta);
				}
			}
						
			else {
				Carta carta = jogador.verCartasNaMao().get(carta_escolhida);
				if(!verificaCartaEvocavel(carta)) {									//Se for um feitico, a carta podera ser jogada
					if(consomeMana(jogador, carta)) {
						adcCartasEvocadas(carta, jogador);
						carta.cartaEvocada(this, jogador);
						jogador.removerCartadaMao(carta);
					}	
				}
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
	
	protected boolean consomeMana(Jogador jogador, Carta carta_jogada) {
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
		
	protected boolean verificaCartaEvocavel(Carta carta_recebida) {
		if(carta_recebida instanceof Feiticos) {
			return false;
		}
		return true;
	}
	
	protected Jogador jogadorAtacante(Jogador jogador_a, Jogador jogador_b) {	
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
	
	protected Jogador jogadorDefensor(Jogador jogador_ataq) {		
		if(this.jogador1.equals(jogador_ataq)) {
			return this.jogador2;
		}
		else {
			return this.jogador1;
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
	
	private void imprimeJogador(Jogador jogador) {
		System.out.print("*|> JOGADOR: "+ jogador.verNome()+" ");
		System.out.print("> MANA: "+ jogador.verMana()+" ");
		System.out.print("> MANA FEITICO: "+ jogador.verManaFeitico()+" ");
		System.out.print("> DECK: "+ jogador.verDeck().verCartas().size()+"|*");
		System.out.println("");
		System.out.println("");
	}
		
	public void imprimeCampo() {
		System.out.println("");
		
		System.out.println("*****************************************************************************************************************");
		System.out.println("");
		System.out.println("		Atacante: "+this.jogador1.verAtaque()+" 	|"+this.jogador1.verNome()+"|		-Vida: "+this.jogador1.verVida()+" -Mana: "+this.jogador1.verMana()+" -Mana feitiço: "+this.jogador1.verManaFeitico()+" -Deck: "+this.jogador1.verDeck().verCartas().size());
		
		System.out.println("");
		
		for(int i = 0; i < this.cartas_evocadas1.size(); i ++) {
			System.out.print("|"+this.cartas_evocadas1.get(i).verNome()+" "+this.cartas_evocadas1.get(i).verVidaAtual() +" "+this.cartas_evocadas1.get(i).verDanoAtual()+"|   ");
		}
		System.out.println("");
		
		System.out.println("======================================================================================================== Rodada: "+this.rodada);
		
		
		for(int i = 0; i < this.cartas_evocadas2.size(); i ++) {
			System.out.print("|"+this.cartas_evocadas2.get(i).verNome()+" "+this.cartas_evocadas2.get(i).verVidaAtual() +" "+this.cartas_evocadas2.get(i).verDanoAtual()+"|   ");
		}
		System.out.println("");
		System.out.println("");
		
		
		System.out.println("		Atacante: "+this.jogador2.verAtaque()+"		 |"+this.jogador2.verNome()+"|		-Vida: "+this.jogador2.verVida()+" -Mana: "+this.jogador2.verMana()+" -Mana feitiço: "+this.jogador2.verManaFeitico()+" -Deck: "+this.jogador2.verDeck().verCartas().size());
		System.out.println("");
		System.out.println("*****************************************************************************************************************");
		System.out.println("");
		
	}
	
	private void reiniciaMao(Jogador jogador) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println(jogador.verNome()+" deseja pegar novas cartas? s/n ");
		String resposta = scan.next();
		
		
		if(resposta.equals("s")) {
			System.out.println("Quantas cartas deseja trocar? ");
			int quant = scan.nextInt();
			jogador.reiniciarCartasNaMao(quant);
		}
		
		//scan.close();
	}

		
	//Valida o numero de cartas na mesa.
	protected boolean checaNumeroCartasEvocadas(int numero_cartas, int tipo) {
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
	
	protected void definirManaInicial() {
		if (rodada < 10) {
			jogador1.definirMana(rodada);
			jogador2.definirMana(rodada);
		} else {
			jogador1.definirMana(10);
			jogador2.definirMana(10);
		}
	}
	
	protected void definirManaFinal(Jogador jogador) {
		if ((jogador.verMana() > 3)) {
			jogador.definirManaFeitico(3);
		}	
		else if (jogador.verMana() > 0) {
			int mana_acumulada = jogador.verManaFeitico() + jogador.verMana();
			if(mana_acumulada > 3) {
				jogador.definirManaFeitico(3);
			}
			
			else {
				jogador.definirManaFeitico(mana_acumulada);
			}	
		jogador.definirMana(0);
		}
	}
	

}

