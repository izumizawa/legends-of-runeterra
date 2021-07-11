package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Carta.Feiticos;
import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Jogador.Jogador;


public class Tabuleiro {
	private Jogador jogador1, jogador2;
	private ArrayList <Carta> cartasEvocadas1, cartasEvocadas2, cartasAtaque, cartasDefesa;
	private int rodada;
	private boolean bloquearDano1, bloquearDano2;
	
	public Tabuleiro(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.cartasEvocadas1= new ArrayList<>();
		this.cartasEvocadas2 = new ArrayList<>();
		this.cartasAtaque = new ArrayList<>();
		this.cartasDefesa = new ArrayList<>();
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
		return this.cartasAtaque;
	}
	
	public ArrayList<Carta> verCartasDefesa(){
		return this.cartasDefesa;
	}
		
	
	public boolean verBloqueioDano(Jogador jogador) {
		if(this.jogador1.equals(jogador)) {
			return this.bloquearDano1;
		}
		return this.bloquearDano2;
	}
	
	
	public void defBloqueioDano(Jogador jogador, boolean valor) {
		if(this.jogador1.equals(jogador)) {
			this.bloquearDano1 = valor;
			
		}
		else {
			this.bloquearDano2 = valor;
		}
	}
	
	// Adiciona a carta a� mesa. Explicitar se e jogador 1 ou 2.
	public void adcCartasEvocadas(Carta cartaAbaixada, Jogador jogador) {
		if(jogador.equals(jogador1)) {
			if(checaNumeroCartasEvocadas(this.cartasEvocadas1.size(), 1)) {
				this.cartasEvocadas1.add(cartaAbaixada);
				return; 
			}
		}
		
		else if(jogador.equals(jogador2)) {
			if(checaNumeroCartasEvocadas(this.cartasEvocadas2.size(), 1)) {
				this.cartasEvocadas2.add(cartaAbaixada);
				return;
			}
		}
		return;
	}
		
	private void adcCartasCombate(Carta carta, ArrayList<Carta> combate) {
		combate.add(carta);
	}
	
	//Remove carta da mesa.
	public void rmvCartasEvocadas(Carta cartaRemovida, Jogador jogador) {
		if(jogador.equals(jogador1)) {
			if(checaNumeroCartasEvocadas(this.cartasEvocadas1.size(), 0)) {
				this.cartasEvocadas1.remove(cartaRemovida);
			}
		}
		
		else if(jogador.equals(jogador2)) {
			if(checaNumeroCartasEvocadas(this.cartasEvocadas2.size(), 0)) {
				this.cartasEvocadas2.remove(cartaRemovida);
			}
		}
		
		else {
			return;
		}
	}
	
	private void rmvEfeitosEvocados() {
		for(int i = 0; i < this.cartasEvocadas1.size(); i ++) {
			Carta carta = this.cartasEvocadas1.get(i);
			carta.removerEfeitos(this, jogador1);
		}
		
		for(int i = 0; i < this.cartasEvocadas2.size(); i ++) {
			Carta carta = this.cartasEvocadas2.get(i);
			carta.removerEfeitos(this, jogador2);
		}
	}
	
	private void rmvEfeitosCombate(Jogador jogadorAtaq, Jogador jogadorDef) {
		for(int i = 0; i < this.cartasAtaque.size(); i ++) {
			Carta carta = this.cartasAtaque.get(i);
			carta.removerEfeitos(this, jogadorAtaq);
		}
		
		for(int i = 0; i < this.cartasDefesa.size(); i ++) {
			Carta carta = this.cartasDefesa.get(i);
			carta.removerEfeitos(this, jogadorDef);
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
				
			Jogador jogadorAtaq = jogadorAtacante(this.jogador1, this.jogador2);
			Jogador jogadorDef = jogadorDefensor(jogadorAtaq);
			imprimeCampo();
			
			turnoJogada(jogadorAtaq);
			turnoAtaque(jogadorAtaq);
			
			turnoJogada(jogadorDef);
			turnoDefesa(jogadorDef);
			
			turnoBatalha(jogadorAtaq, jogadorDef);
			
			definirManaFinal(jogadorAtaq);
			definirManaFinal(jogadorDef);
		}	
	}
			
	private void turnoBatalha(Jogador jogadorAtaq, Jogador jogadorDef) {
		System.out.println("TURNO DE BATALHA");
		
		imprimeBatalha(this.cartasAtaque);
		imprimeBatalha(this.cartasDefesa);
				
		for(int i = 0; i < this.cartasAtaque.size(); i++) {
			Seguidores seguidorAtaq = (Seguidores) this.cartasAtaque.get(i);
			if(i < this.cartasDefesa.size()) {
				Seguidores seguidorDef = (Seguidores) this.cartasDefesa.get(i);			
				seguidorAtaq.atacarInimigo(this, jogadorAtaq, seguidorDef);				
			}
			else {
				seguidorAtaq.atacarInimigo(this, jogadorDef);
			}
		}
		
		rmvEfeitosEvocados();
		rmvEfeitosCombate(jogadorAtaq, jogadorDef);
				
		for(int i = 0; i < this.cartasAtaque.size(); i++) {
			Seguidores seguidorAtaq = (Seguidores) this.cartasAtaque.get(i);
			if(i < this.cartasDefesa.size()) {
				Seguidores seguidorDef = (Seguidores) this.cartasDefesa.get(i);
				if(seguidorDef.verVidaAtual() > 0) {
					adcCartasEvocadas(seguidorDef, jogadorDef);
				}			
			}
			if(seguidorAtaq.verVidaAtual() > 0) {
				adcCartasEvocadas(seguidorAtaq, jogadorAtaq);
			}
		}
		
		this.cartasAtaque.removeAll(cartasAtaque);
		this.cartasDefesa.removeAll(cartasDefesa);	
	}
	
	private void turnoDefesa(Jogador defensor) {
		
		System.out.println("Deseja defender? s/n");
		String resposta = leInformacaoStr();
		int numeroCarta = 0;
		boolean iteracao = true;
		ArrayList <Carta> cartasEvocadas = encontraCartasEvocadas(defensor);
		
		
		if(resposta.equals("s")) {
			
			System.out.println("Escolha o numero das cartas para colocar no campo de batalha!");
			
			while(iteracao) {
				imprimeCartasEvocadas(cartasEvocadas);
				System.out.println("Pressione 0 para concluir");
				numeroCarta = (leInformacaoInt() - 1);
				
				if((numeroCarta == -1) || (numeroCarta > cartasEvocadas.size())) {
					iteracao = false;
				}
				
				else  {
					adcCartasCombate(cartasEvocadas.get(numeroCarta), this.cartasDefesa);
					rmvCartasEvocadas(cartasEvocadas.get(numeroCarta), defensor);
				}	
			}		
		}		
	}
	
	private void turnoAtaque(Jogador atacante) {
		
		System.out.println("Deseja atacar? s/n");
		String resposta = leInformacaoStr();
		int numeroCarta = 0;
		boolean iteracao = true;
		ArrayList <Carta> cartasEvocadas = encontraCartasEvocadas(atacante);
		
		
		if(resposta.equals("s")) {
			
			System.out.println("Escolha o numero das cartas para colocar no campo de batalha!");
			
			while(iteracao) {
				imprimeCartasEvocadas(cartasEvocadas);
				System.out.println("Pressione 0 para concluir");
				numeroCarta = (leInformacaoInt() - 1);
				
				if((numeroCarta == -1) || (numeroCarta > cartasEvocadas.size())) {
					iteracao = false;
				}
				
				else  {
					adcCartasCombate(cartasEvocadas.get(numeroCarta), this.cartasAtaque);
					rmvCartasEvocadas(cartasEvocadas.get(numeroCarta), atacante);
				}	
			}		
		}
	}
	
	private void turnoJogada(Jogador jogador) {
		jogador.comprarCarta();
		
		boolean imprimeMao = true;												//Decisao para imprimir a mao do jogador
		int cartaEscolhida = 0;													//Numero da ca rta escolhida
		ArrayList <Carta> cartasEvocadas = encontraCartasEvocadas(jogador);		//Mesa do jogador
		
		while(imprimeMao) {
			imprimeJogador(jogador);
			System.out.println("Informe o numero da carta que deseja jogar: ");
			imprimeCartasdaMao(jogador);
			System.out.println("PULAR: Digite 0");
			cartaEscolhida = (leInformacaoInt() - 1);
					
			if(cartaEscolhida == -1) {
				imprimeMao = false;
			}
			
			else if(checaNumeroCartasEvocadas(cartasEvocadas.size(), 1) && (verificaCartaEvocavel(jogador.verCartasNaMao().get(cartaEscolhida)))) {
				Carta carta = jogador.verCartasNaMao().get(cartaEscolhida);
				if(consomeMana(jogador, carta)) {
					adcCartasEvocadas(carta, jogador);
					carta.cartaEvocada(this, jogador);
					jogador.removerCartadaMao(carta);
				}
			}
						
			else {
				Carta carta = jogador.verCartasNaMao().get(cartaEscolhida);
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
			return cartasEvocadas1;
		}
		else {
			return cartasEvocadas2;
		}		
	}
	
	private boolean consomeMana(Jogador jogador, Carta cartaJogada) {
		int custoMana = cartaJogada.verCustoMana();
		int manaJogador = jogador.verMana();
		int manaFeitico = jogador.verManaFeitico();
		boolean evocavel = verificaCartaEvocavel(cartaJogada);
		if((custoMana <= manaJogador) && evocavel) {
			int manaAtualizada = (manaJogador - custoMana);
			jogador.definirMana(manaAtualizada);
			return true;
		}
		else if( !evocavel && (custoMana <= manaFeitico)) {
			int manaAtualizada = manaFeitico - custoMana;
			jogador.definirManaFeitico(manaAtualizada);
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
		
	private boolean verificaCartaEvocavel(Carta cartaRecebida) {
		if(cartaRecebida instanceof Feiticos) {
			return false;
		}
		return true;
	}
	
	private Jogador jogadorAtacante(Jogador jogadorA, Jogador jogadorB) {	
		if(jogadorA.verAtaque() == true) {
			jogadorB.definirAtaque(true);
			jogadorA.definirAtaque(false);
			return jogadorB;
		}
		else {
			jogadorB.definirAtaque(false);
			jogadorA.definirAtaque(true);
			return jogadorA;
		}
		
	}
	
	private Jogador jogadorDefensor(Jogador jogadorAtaq) {		
		if(this.jogador1.equals(jogadorAtaq)) {
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
		
		for(int i = 0; i < this.cartasEvocadas1.size(); i ++) {
			System.out.print("|"+this.cartasEvocadas1.get(i).verNome()+" "+this.cartasEvocadas1.get(i).verVidaAtual() +" "+this.cartasEvocadas1.get(i).verDanoAtual()+"|   ");
		}
		System.out.println("");
		
		System.out.println("======================================================================================================== Rodada: "+this.rodada);
		
		
		for(int i = 0; i < this.cartasEvocadas2.size(); i ++) {
			System.out.print("|"+this.cartasEvocadas2.get(i).verNome()+" "+this.cartasEvocadas2.get(i).verVidaAtual() +" "+this.cartasEvocadas2.get(i).verDanoAtual()+"|   ");
		}
		System.out.println("");
		System.out.println("");
		
		
		System.out.println("		Atacante: "+this.jogador2.verAtaque()+"		 |"+this.jogador2.verNome()+"|		-Vida: "+this.jogador2.verVida()+" -Mana: "+this.jogador2.verMana()+" -Mana feitiço: "+this.jogador2.verManaFeitico()+" -Deck: "+this.jogador2.verDeck().verCartas().size());
		System.out.println("");
		System.out.println("*****************************************************************************************************************");
		System.out.println("");
		
	}
	
	private void imprimeBatalha(ArrayList<Carta> cartas) {
		for(int i = 0; i < cartas.size(); i ++) {
			System.out.println("Carta "+ (i+1));
			System.out.println("Nome: "+cartas.get(i).verNome());
			System.out.println("Vida "+cartas.get(i).verVidaAtual());
			System.out.println("Dano: "+cartas.get(i).verDano());
			System.out.println("");
		}
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
	private boolean checaNumeroCartasEvocadas(int numeroCartas, int tipo) {
		if(tipo == 1) {
			if(numeroCartas < 6) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else if(tipo == 0) {
			if (numeroCartas > 0) {
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
		if ((jogador.verMana() > 3)) {
			jogador.definirManaFeitico(3);
		}	
		else if (jogador.verMana() > 0) {
			int manaAcumulada = jogador.verManaFeitico() + jogador.verMana();
			if(manaAcumulada > 3) {
				jogador.definirManaFeitico(3);
			}
			
			else {
				jogador.definirManaFeitico(manaAcumulada);
			}	
		jogador.definirMana(0);
		}
	}
}

