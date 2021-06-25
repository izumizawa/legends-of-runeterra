package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;

public class Campeoes extends Seguidores{
	private int nCondicao;
	private int andamentoCondicao;
	private Boolean evoluido;
	private int tipo;

	public Campeoes(String nome,Status carta, int condicao) {
		super(nome, carta);
		nCondicao = condicao;
		andamentoCondicao = 0;
		evoluido = false;
	}
	
	//Prototipo da evolução do personagem
	@Override
	public void atacarInimigo(Seguidores inimigo) {
		super.atacarInimigo(inimigo);
		int dano = verDano();
		if(tipo == 1) {
			andamentoCondicao += dano;
		}
		Boolean evoluiu = verificaEvolucao();
		if(evoluiu) {
			definirDano(dano + nCondicao);
		}
	}
	
	private Boolean verificaEvolucao() {
		if(andamentoCondicao>=nCondicao) 
		{
			return true;
		}
		return false;
	}
	
	public Boolean verEvoluido() {
		return evoluido;
	}
	

}
