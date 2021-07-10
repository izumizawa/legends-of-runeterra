package ProjetoFinal.Evoluir;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Efeitos.Efeito;
import ProjetoFinal.Tracos.Traco;

public class AdicionarHabilidade implements Evolucao {
	private Traco traco;
	private Efeito efeito;
	
	public AdicionarHabilidade(Traco novo) {
		traco = novo;
		efeito = null;
	}
	public AdicionarHabilidade(Efeito novo) {
		traco = null;
		efeito = novo;
	}
	@Override
	public void evoluir(Seguidores card) {
		// TODO Auto-generated method stub
		if(traco!=null) {
			card.adicionarTraco(traco);
		}
		else {
			card.adicionarEfeito(efeito);
		}
		
	}

}
