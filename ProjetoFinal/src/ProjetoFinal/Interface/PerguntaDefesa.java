package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.TabuleiroInterface;
import swing2swt.layout.FlowLayout;

public class PerguntaDefesa extends Dialog {

	protected Object result;
	protected Shell shell;
	protected TabuleiroInterface tabuleiroInterface;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PerguntaDefesa(Shell parent, int style, TabuleiroInterface tabuleiroInterface) {
		super(parent, style);
		this.tabuleiroInterface = tabuleiroInterface;
		setText("Defesa");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String pergunta, Jogador jogador) {
		createContents(pergunta, jogador);
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
	private void createContents(String pergunta, Jogador jogador) {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 100);
		shell.setText(getText());
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText(pergunta);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
			}
		});
		btnNewButton.setText("Nao");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tabuleiroInterface.turnoDefesa(jogador, shell);
				shell.close();
			}
		});
		btnNewButton_1.setText("Sim");

	}

}
