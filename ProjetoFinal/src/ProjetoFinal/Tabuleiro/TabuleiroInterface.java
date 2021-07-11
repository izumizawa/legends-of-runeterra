package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Interface.CampoInterface;
import ProjetoFinal.Interface.EvocacaoDeCarta;
import ProjetoFinal.Interface.JogadaAtaque;
import ProjetoFinal.Interface.JogadaDefesa;
import ProjetoFinal.Interface.PerguntaAtaque;
import ProjetoFinal.Interface.PerguntaDefesa;
import ProjetoFinal.Interface.ReiniciaMao;
import ProjetoFinal.Jogador.Jogador;

public class TabuleiroInterface extends Tabuleiro {
	
	private int cartaEscolhida;
	
	public TabuleiroInterface(Jogador jogador1, Jogador jogador2) {
		super(jogador1, jogador2);
		this.cartaEscolhida = 0;
	}
	
	public int verCartaEscolhida() {
		return cartaEscolhida;
	}

	public void definirCartaEscolhida(int cartaEscolhida) {
		this.cartaEscolhida = cartaEscolhida;
	}

	// Metodos para interface grafica SWT
	
	public void iniciaJogo(Shell shell) {
		verJogador1().iniciarCartasNaMao();
		verJogador2().iniciarCartasNaMao();
	
		ReiniciaMao reiniciaMao1 = new ReiniciaMao(shell, SWT.NONE, verJogador1());
		reiniciaMao1.open(verJogador1());
		ReiniciaMao reiniciaMao2 = new ReiniciaMao(shell, SWT.NONE, verJogador2());
		reiniciaMao2.open(verJogador2());
	}
	
	public void abreCampo(Shell shell) {
		CampoInterface campo = new CampoInterface(this);
		campo.open();
	}
	
	private void turnoJogada(Jogador jogador, Shell shell) {
		jogador.comprarCarta();
		
		boolean imprime_mao = true;													//Decisao para imprimir a mao do jogador
		ArrayList<Carta> cartas_evocadas = encontraCartasEvocadas(jogador);		//Mesa do jogador
		
		EvocacaoDeCarta jogada = new EvocacaoDeCarta(shell, SWT.NONE, this, jogador);
		while(imprime_mao) {
			jogada.open("mao", jogador.verCartasNaMao());					
			if(cartaEscolhida == -1) {
				imprime_mao = false;
			}
			
			else if(checaNumeroCartasEvocadas(cartas_evocadas.size(), 1) && (verificaCartaEvocavel(jogador.verCartasNaMao().get(cartaEscolhida)))) {
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
	
	private void abreAtaque(Shell shell, String pergunta, Jogador jogador) {
		PerguntaAtaque janelaPergunta = new PerguntaAtaque(shell, SWT.NONE, this);
		janelaPergunta.open(pergunta, jogador);
	}
	
	private void abreDefesa(Shell shell, String pergunta, Jogador jogador) {
		PerguntaDefesa janelaPergunta = new PerguntaDefesa(shell, SWT.NONE, this);
		janelaPergunta.open(pergunta, jogador);
	}

	
	public void turnoAtaque(Jogador atacante, Shell shell) {
		System.out.println("Turno ataque");
		boolean iteracao = true;
		ArrayList <Carta> cartas_evocadas = encontraCartasEvocadas(atacante);
		
		JogadaAtaque jogada = new JogadaAtaque(shell, SWT.NONE, this, atacante);
		
		
		while(iteracao) {
			jogada.open(cartas_evocadas, verCartasAtaque());
			
			if((cartaEscolhida == -1) || (cartaEscolhida > cartas_evocadas.size()))
				iteracao = false;
			
			else  {
				adcCartasCombate(cartas_evocadas.get(cartaEscolhida), verCartasAtaque());
				rmvCartasEvocadas(cartas_evocadas.get(cartaEscolhida), atacante);
			}	
		}		
	}
	
	public void turnoDefesa(Jogador defensor, Shell shell) {
		System.out.println("Turno defesa");
		boolean iteracao = true;
		ArrayList <Carta> cartas_evocadas = encontraCartasEvocadas(defensor);	
		JogadaDefesa jogada = new JogadaDefesa(shell, SWT.NONE, this, defensor);
		
		while(iteracao) {
			jogada.open(cartas_evocadas, verCartasDefesa());
			
			if((cartaEscolhida == -1) || (cartaEscolhida > cartas_evocadas.size())) 
				iteracao = false;
	
			else  {
				adcCartasCombate(cartas_evocadas.get(cartaEscolhida), verCartasDefesa());
				rmvCartasEvocadas(cartas_evocadas.get(cartaEscolhida), defensor);
			}	
		}		
				
	}
	
	public void rodadasJogo(Shell shell) {
		int vida1 = verJogador1().verVida();
		int vida2 = verJogador2().verVida();

		while((vida1 >= 0) && (vida2 >= 0)) {
			this.rodada ++;
			System.out.println("Rodada " + this.rodada);
			definirManaInicial();
				
			Jogador jogador_ataq = jogadorAtacante(verJogador1(), verJogador2());
			Jogador jogador_def = jogadorDefensor(jogador_ataq);
			abreCampo(shell);
			
			turnoJogada(jogador_ataq, shell);
			abreAtaque(shell, jogador_ataq.verNome() + ", deseja atacar?", jogador_ataq);
			
			turnoJogada(jogador_def, shell);
			abreDefesa(shell, jogador_def.verNome() + ", deseja defender?", jogador_def);
		
//			turnoBatalha();
			
			definirManaFinal(jogador_ataq);
			definirManaFinal(jogador_def);
		}	
	}
}
