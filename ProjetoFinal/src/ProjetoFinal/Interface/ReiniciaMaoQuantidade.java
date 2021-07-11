package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import ProjetoFinal.Jogador.Jogador;
import swing2swt.layout.FlowLayout;

public class ReiniciaMaoQuantidade extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ReiniciaMaoQuantidade(Shell parent, int style, Jogador jogador) {
		super(parent, style);
		setText(jogador.verNome() + ": Reiniciar mao");
	}

	/**
	 * Open the dialog.
	 * @param jogador 
	 * @return the result
	 */
	public Object open(Jogador jogador) {
		createContents(jogador);
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
	private void createContents(Jogador jogador) {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 50);
		shell.setText(getText());
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText(jogador.verNome() + ", quantas cartas deseja trocar?");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMaximum(4);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				jogador.reiniciarCartasNaMao(spinner.getSelection());
				shell.close();
			}
		});
		btnNewButton.setText("Confirmar");

	}

}
