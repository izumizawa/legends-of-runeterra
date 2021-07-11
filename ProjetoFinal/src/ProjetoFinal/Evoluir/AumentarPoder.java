package ProjetoFinal.Evoluir;

import ProjetoFinal.Carta.Campeoes;


public class AumentarPoder implements Evolucao{
	private int poder;

	public AumentarPoder(int n) {
		poder = n;
	}
	@Override
	public void evoluir(Campeoes card) {
		// TODO Auto-generated method stub
		int danoAtual = card.verDanoAtual();
		int novoDano = danoAtual + poder;
		card.definirDanoAtual(novoDano);
	}

}
