package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import ProjetoFinal.Jogador.Jogador;

public class JogadorInterface extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public JogadorInterface(Composite parent, int style, Jogador jogador) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.widthHint = 211;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(141);
		tblclmnNewColumn.setText("Parametro");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Valor");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] { "Jogador", jogador.verNome() });
		
		TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText(new String[] {"Turno", verificarAtacante(jogador) });
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] { "Mana", String.valueOf(jogador.verMana()) });
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(new String[] { "Mana de feitico", String.valueOf(jogador.verManaFeitico()) });
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(new String[] { "Deck", String.valueOf(jogador.verDeck().verCartas().size())});

	}
	
	private String verificarAtacante(Jogador jogador) {
		String papel = "Defensor";
		if (jogador.verAtaque())
			papel = "Atacante";
		return papel;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
