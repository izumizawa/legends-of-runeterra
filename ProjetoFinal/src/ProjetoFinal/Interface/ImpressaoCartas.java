package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import ProjetoFinal.Carta.Carta;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class ImpressaoCartas extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ImpressaoCartas(Composite parent, int style, java.util.List<Carta> cartas, String label) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 5);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText(label);
		
		List list = new List(this, SWT.BORDER);
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(0, 297);
		fd_list.top = new FormAttachment(0, 27);
		fd_list.left = new FormAttachment(0, 10);
		list.setLayoutData(fd_list);
		if (cartas != null)
			for (Carta carta : cartas) {
				list.add(carta.verNome() + " / Mana: " + carta.verCustoMana() + " / P: " + carta.verDano() + " / V: " + carta.verVidaAtual());
			}
		else 
			list.add("Nenhuma carta no momento");
			
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
