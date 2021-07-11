package ProjetoFinal.Interface;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.TabuleiroInterface;
import swing2swt.layout.FlowLayout;

public class JogadaDefesa extends Dialog {

	protected Object result;
	protected Shell shell;
	protected Jogador jogador;
	protected TabuleiroInterface tabuleiroInterface;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public JogadaDefesa(Shell parent, int style, TabuleiroInterface tabuleiro, Jogador jogador) {
		super(parent, style);
		setText("Defesa de " + jogador.verNome());
		this.tabuleiroInterface = tabuleiro;
		this.jogador = jogador;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(List<Carta> cartasEvocadas, List<Carta> cartasAtaque) {
		createContents(cartasEvocadas, cartasAtaque);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents(List<Carta> cartasEvocadas, List<Carta> cartasDefesa) {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(800, 400);
		shell.setText(getText());
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JogadorInterface ijogador1 = new JogadorInterface(shell, SWT.NONE, jogador);
		ImpressaoCartas cartasJogador1 = new ImpressaoCartas(shell, SWT.NONE, cartasEvocadas, "Cartas evocadas de "+ jogador.verNome());
		ImpressaoCartas cartasDefesaJogador1 = new ImpressaoCartas(shell, SWT.NONE, cartasDefesa, "Cartas de defesa de "+ jogador.verNome());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("Informe o numero da carta que deseja jogar: ");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMaximum(jogador.verCartasNaMao().size());
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tabuleiroInterface.definirCartaEscolhida(spinner.getSelection()-1);
				shell.close();
			}
		});
		btnNewButton.setText("Confirmar");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("Escolha 0 para pular");

	}

}
