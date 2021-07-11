package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Jogador.Jogador;
import swing2swt.layout.FlowLayout;

public class ReiniciaMao extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 * @param jogador 
	 * @param jogador 
	 */
	public ReiniciaMao(Shell parent, int style, Jogador jogador) {
		super(parent, style);
		setText(jogador.verNome() + ": Cartas da mao");
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
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Nome");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Valor de mana");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Poder");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Vida");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(verCarta(jogador.verCartasNaMao().get(0)));
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(verCarta(jogador.verCartasNaMao().get(1)));
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(verCarta(jogador.verCartasNaMao().get(2)));
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(verCarta(jogador.verCartasNaMao().get(3)));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText(jogador.verNome() + ", gostaria de trocar uma ou mais cartas?");
		
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
				ReiniciaMaoQuantidade reiniciaMaoQuantidade = new ReiniciaMaoQuantidade(shell, getStyle(), jogador);
				reiniciaMaoQuantidade.open(jogador);
				shell.close();
			}
		});
		btnNewButton_1.setText("Sim");
		

	}

	private String[] verCarta(Carta carta) {
		String[] parametros = new String[] { carta.verNome(), String.valueOf(carta.verCustoMana()), String.valueOf(carta.verDano()), String.valueOf(carta.verVidaAtual()) };
		return parametros;
	}
}
