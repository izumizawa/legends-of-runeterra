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

public class EvocacaoDeCarta extends Dialog {

	protected Object result;
	protected Shell shell;
	protected Jogador jogador;
	protected TabuleiroInterface tabuleiroInterface;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EvocacaoDeCarta(Shell parent, int style, TabuleiroInterface tabuleiro, Jogador jogador) {
		super(parent, style);
		setText("Jogada de " + jogador.verNome());
		this.tabuleiroInterface = tabuleiro;
		this.jogador = jogador;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String jogada, List<Carta> lista) {
		createContents(jogada, lista);
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
	private void createContents(String jogada, List<Carta> lista) {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(600, 400);
		shell.setText(getText());
		shell.setLayout(new GridLayout(5, false));
		
				JogadorInterface ijogador1 = new JogadorInterface(shell, SWT.NONE, jogador);
		ImpressaoCartas cartasJogador1 = new ImpressaoCartas(shell, SWT.NONE, lista, "Cartas de " + jogada + " de "+ jogador.verNome());
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("Informe o numero da carta que deseja jogar: ");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMaximum(jogador.verCartasNaMao().size());
		new Label(shell, SWT.NONE);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tabuleiroInterface.definirCartaEscolhida(spinner.getSelection()-1);
				shell.close();
			}
		});
		btnNewButton.setText("Confirmar");
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("Escolha 0 para pular");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

	}

}
