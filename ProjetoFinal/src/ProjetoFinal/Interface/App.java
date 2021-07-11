package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Deck.Deck;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class App {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Game started!");
		System.out.println("");

		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(1000, 600);
		shell.setText("Legends of Runeterra");
		
	
		Status status1 = new Status(1,1,1);	
		Status status2 = new Status(1,2,2);
		Status status3 = new Status(1,3,3);
		Status status4 = new Status(1,4,4);
		Status status5 = new Status(1,5,5);
		Status status6 = new Status(1,6,6);
		Status status7 = new Status(1,7,7);
		Status status8 = new Status(1,8,8);
		Status status9 = new Status(1,9,9);
		Status status10 = new Status(1,10,10);
		Status status11 = new Status(1,11,11);
		Status status12 = new Status(1,12,12);
		Status status13 = new Status(1,13,13);
		Status status14 = new Status(1,14,14);
		Status status15 = new Status(1,15,15);
		Status status16 = new Status(1,16,16);
		Status status17 = new Status(1,17,17);
		Status status18 = new Status(1,18,18);
		Status status19 = new Status(1,19,19);
		Status status20 = new Status(1,20,20);
		
		Carta carta1 = new Carta("carta 1", status1);
		Carta carta2 = new Carta("carta 2", status2);
		Carta carta3 = new Carta("carta 3", status3);
		Carta carta4 = new Carta("carta 4", status4);
		Carta carta5 = new Carta("carta 5", status5);
		Carta carta6 = new Carta("carta 6", status6);
		Carta carta7 = new Carta("carta 7", status7);
		Carta carta8 = new Carta("carta 8", status8);
		Carta carta9 = new Carta("carta 9", status9);
		Carta carta10 = new Carta("carta 10", status10);
		Carta carta11 = new Carta("carta 11", status11);
		Carta carta12 = new Carta("carta 12", status12);
		Carta carta13 = new Carta("carta 13", status13);
		Carta carta14 = new Carta("carta 14", status14);
		Carta carta15 = new Carta("carta 15", status15);
		Carta carta16 = new Carta("carta 16", status16);
		Carta carta17 = new Carta("carta 17", status17);
		Carta carta18 = new Carta("carta 18", status18);
		Carta carta19 = new Carta("carta 19", status19);
		Carta carta20 = new Carta("carta 20", status20);
		
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		
		deck1.adicionarCarta(carta1);
		deck1.adicionarCarta(carta2);
		deck1.adicionarCarta(carta3);
		deck1.adicionarCarta(carta4);
		deck1.adicionarCarta(carta5);
		deck1.adicionarCarta(carta6);
		deck1.adicionarCarta(carta7);
		deck1.adicionarCarta(carta8);
		deck1.adicionarCarta(carta9);
		deck1.adicionarCarta(carta10);
		
		deck2.adicionarCarta(carta11);
		deck2.adicionarCarta(carta12);
		deck2.adicionarCarta(carta13);
		deck2.adicionarCarta(carta14);
		deck2.adicionarCarta(carta15);
		deck2.adicionarCarta(carta16);
		deck2.adicionarCarta(carta17);
		deck2.adicionarCarta(carta18);
		deck2.adicionarCarta(carta19);
		deck2.adicionarCarta(carta20);
		
		Jogador jogador1 = new Jogador("Jogador 1", deck1);
		IJogador ijogador1 = new IJogador(shell, SWT.NONE, jogador1);
		ijogador1.setBounds(0, 0, 300, 150);
		
		Jogador jogador2 = new Jogador("Jogador 2", deck2);
		IJogador ijogador2 = new IJogador(shell, SWT.NONE, jogador2);
		ijogador2.setBounds(0, 400, 300, 150);

		Tabuleiro tabuleiro = new Tabuleiro(jogador1, jogador2);

		ImpressaoCartas cartasDefesa = new ImpressaoCartas(shell, SWT.NONE, tabuleiro.verCartasDefesa());
		ImpressaoCartas cartasAtaque = new ImpressaoCartas(shell, SWT.NONE, tabuleiro.verCartasAtaque());
		cartasAtaque.setBounds(0, 0, 0, 0);
		
		tabuleiro.iniciaJogoInterface(shell, SWT.NONE);
//		tabuleiro.rodadasJogo();
		
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		System.out.println("Game terminated. Bye!");
	}
}
