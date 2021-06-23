package ProjetoFinal.Carta;

import ProjetoFinal.ItensAdicionais.Status;

public class Campeoes extends Seguidores{
	private int nCondicao;
	private int andamentoCondicao;
	private Boolean evoluido;

	public Campeoes(String nome,Status carta, int condicao) {
		super(nome, carta);
		nCondicao = condicao;
		andamentoCondicao = 0;
		evoluido = false;
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
