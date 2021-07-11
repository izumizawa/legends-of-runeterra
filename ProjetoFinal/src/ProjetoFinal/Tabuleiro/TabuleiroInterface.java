package ProjetoFinal.Tabuleiro;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Interface.CampoInterface;
import ProjetoFinal.Interface.Jogada;
import ProjetoFinal.Interface.Pergunta;
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
		
		Jogada jogada = new Jogada(shell, SWT.NONE, this, jogador);
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
	
	private void abrePergunta(Shell shell, String pergunta) {
		Pergunta janelaPergunta = new Pergunta(shell, SWT.NONE);
		janelaPergunta.open(pergunta);
	}
	
	public void rodadasJogo(Shell shell) {
		int vida1 = verJogador1().verVida();
		int vida2 = verJogador2().verVida();

		while((vida1 >= 0) && (vida2 >= 0)) {
			this.rodada ++;
			definirManaInicial();
				
			Jogador jogador_ataq = jogadorAtacante(verJogador1(), verJogador2());
			Jogador jogador_def = jogadorDefensor(jogador_ataq);
			abreCampo(shell);
			
			turnoJogada(jogador_ataq, shell);
//			turnoAtaque(jogador_ataq, shell);
			
			turnoJogada(jogador_def, shell);
//			turnoDefesa(jogador_def, shell);
//			
//			turnoBatalha();
			
			definirManaFinal(jogador_ataq);
			definirManaFinal(jogador_def);
		}	
	}
}
