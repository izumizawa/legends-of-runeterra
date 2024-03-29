package ProjetoFinal.Evoluir;

import ProjetoFinal.Carta.Campeoes;

public class AumentarVida implements Evolucao{
	private int vida;
	
	public AumentarVida(int n) {
		vida = n;
	}
	
	
	@Override
	public void evoluir(Campeoes card) {
		// TODO Auto-generated method stub
		int vidaAtual = card.verVidaAtual();
		int novaVida = vidaAtual + vida;
		int vidaTotal = card.verVidaTotal();
		int novaVidaTotal = vidaTotal + vida;
		card.definirVidaAtual(novaVida);
		card.definirVidaTotal(novaVidaTotal);
	}

}
