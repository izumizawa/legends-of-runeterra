package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ICarta extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ICarta(Composite parent, int style, ProjetoFinal.Carta.Carta carta) {
		super(parent, style);
		setLayout(new FormLayout());
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(0, 219);
		fd_table.right = new FormAttachment(0, 330);
		fd_table.top = new FormAttachment(0, 5);
		fd_table.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Parametro");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Valor");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] { "Nome", carta.verNome() });
		
//		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
//		tableItem_3.setText(new String[] { "Tipo", "" } );
		
		TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText(new String[] { "Custo de mana", String.valueOf(carta.verCustoMana()) });
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] { "Poder", String.valueOf(carta.verDano()) });
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(new String[] { "Vida", String.valueOf(carta.verVidaAtual()) });
		
//		TableItem tableItem_5 = new TableItem(table, SWT.NONE);
//		tableItem_5.setText("Efeito\n");
		
//		TableCursor tableCursor = new TableCursor(table, SWT.NONE);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
