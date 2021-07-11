package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ProjetoFinal.Tabuleiro.Tabuleiro;
import ProjetoFinal.Tabuleiro.TabuleiroInterface;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CampoInterface {

	protected Shell shell;
	private TabuleiroInterface tabuleiro;

	public CampoInterface(TabuleiroInterface tabuleiroInterface) {
		this.tabuleiro = tabuleiroInterface;
	}

	/**
	 * Launch the application.
	 * @param args
	 * @param Tabuleiro 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args, TabuleiroInterface tabuleiro) {
		try {
			CampoInterface window = new CampoInterface(tabuleiro);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(600, 600);
		shell.setText("Legends of Runeterra: campo");
		shell.setLayout(new GridLayout(4, false));
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("Rodada: " + this.tabuleiro.verRodada());
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		JogadorInterface ijogador1 = new JogadorInterface(shell, SWT.NONE, this.tabuleiro.verJogador1());
		ijogador1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		JogadorInterface ijogador2 = new JogadorInterface(shell, SWT.NONE, this.tabuleiro.verJogador2());
		ijogador2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		ImpressaoCartas cartasJogador1 = new ImpressaoCartas(shell, SWT.NONE, this.tabuleiro.verCartasEvocadas1(), "Cartas evocadas de "+ this.tabuleiro.verJogador1().verNome());

		ImpressaoCartas cartasJogador2 = new ImpressaoCartas(shell, SWT.NONE, this.tabuleiro.verCartasEvocadas2(), "Cartas evocadas de "+ this.tabuleiro.verJogador2().verNome());
		cartasJogador2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
			}
		});
		btnNewButton.setText("Iniciar");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
	}

}
