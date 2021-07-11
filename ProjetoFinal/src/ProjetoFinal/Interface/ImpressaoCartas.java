package ProjetoFinal.Interface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import ProjetoFinal.Carta.Carta;

public class ImpressaoCartas extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ImpressaoCartas(Composite parent, int style, java.util.List<Carta> cartas) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		List list = new List(this, SWT.BORDER);
		if (cartas != null)
			for (Carta carta : cartas) {
				list.add(carta.verNome());
			}
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
