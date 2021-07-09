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
	//Estou usando o tipo como int, mas podemos criar um enum
	
	
	@Override
	public void atacarInimigo(Seguidores inimigo) {
		super.atacarInimigo(inimigo);
		if(tipo == 1) {
			int dano = verDano();
			andamentoCondicao += dano;
		}
		else if(tipo == 2) {
			int vidaInimigo = inimigo.verVidaTotal();
			if(vidaInimigo<=0) {
				andamentoCondicao+=1;
			}
		}
		evoluir();
	}
	
	@Override 
	public void sofrerDano(int dano){
		super.sofrerDano(dano);
		if(tipo == 3) {
			andamentoCondicao+= dano;
		}
	}
	
	private Boolean verificaEvolucao() {
		if(andamentoCondicao>=nCondicao) 
		{
			return true;
		}
		return false;
	}
	
	private void evoluir() {
		if(verificaEvolucao()) {
			if(tipo ==1) {
				definirDanoAtual(nCondicao);
			}
			else if(tipo ==2 ) {
				int vida = verVidaAtual();
				definirVidaAtual(vida + nCondicao);
			}
		}
	}
	
	public Boolean verEvoluido() {
		return evoluido;
	}
	
	

}
