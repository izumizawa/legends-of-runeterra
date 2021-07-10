package ProjetoFinal.Tracos;

import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.Jogador.Jogador;

public class Furia implements Traco{
	private String tipo;
	private Status stat;
	
	public Furia(Status status) {
		stat = status;
	}
	@Override
	public void atacarInimigo(Seguidores card, Seguidores inimigo) {
		// TODO Auto-generated method stub
		card.atacarInimigo(inimigo);
		if(inimigo.verVidaAtual() <= 0) {
			int danoAtual = card.verDanoAtual();
			int vidaAtual = card.verVidaAtual();
			int danoBonus = stat.verAtaque();
			int vidaBonus = stat.verDefesa();
			card.definirDanoAtual(danoAtual + danoBonus);
			card.definirVidaAtual(vidaAtual + vidaBonus);
		}
	}

	@Override
	public void atacarInimigo(Seguidores card, Jogador player) {
		// TODO Auto-generated method stub
		card.atacarInimigo(player);
	}

	@Override
	public String verTipo() {
		// TODO Auto-generated method stub
		return null;
	}

}
