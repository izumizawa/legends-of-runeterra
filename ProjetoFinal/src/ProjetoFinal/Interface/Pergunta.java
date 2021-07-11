package ProjetoFinal.Interface;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import swing2swt.layout.FlowLayout;

public class Pergunta extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Pergunta(Shell parent, int style) {
		super(parent, style);
		setText("Pergunta");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String pergunta) {
		createContents(pergunta);
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
	private void createContents(String pergunta) {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 100);
		shell.setText(getText());
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText(pergunta);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setText("Nao");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setText("Sim");

	}

}
