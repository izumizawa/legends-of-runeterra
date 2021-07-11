package ProjetoFinal.Carta;

import java.util.ArrayList;
import java.util.Iterator;

import ProjetoFinal.Efeitos.Efeito;
import ProjetoFinal.Evoluir.Evolucao;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.ItensAdicionais.TipoEvolucao;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;
import ProjetoFinal.Tracos.Traco;

public class Campeoes extends Seguidores{
	private int nCondicao;
	private int andamentoCondicao;
	private TipoEvolucao tipoEvolucao;
	private Evolucao buffEvolucao;
	private Boolean evoluido;

	public Campeoes(String nome,Status carta, int condicao) {
		super(nome, carta);
		nCondicao = condicao;
		andamentoCondicao = 0;
		evoluido = false;
	}
	public Campeoes(String nome,Status carta, Traco t, Evolucao e,TipoEvolucao tipo,int condicao) {
		super(nome, carta,t);
		nCondicao = condicao;
		tipoEvolucao = tipo;
		buffEvolucao = e;
		andamentoCondicao = 0;
		evoluido = false;
	}
	
	//Prototipo da evolução do personagem
	//Estou usando o tipo como int, mas podemos criar um enum
	
	
	@Override
	public void atacarInimigo(Tabuleiro t, Jogador j,Seguidores inimigo) {
		Jogador oponente = t.verOponente(j);
		Traco tracoCampeao = verTraco();
		boolean atacou = true;
		if(t.verBloqueioDano(oponente)) {
			atacou=false;
		}
		if(tracoCampeao == null) {
			int dano = verDanoAtual();
			boolean bloquear = t.verBloqueioDano(oponente);
			if(!bloquear) {
				inimigo.sofrerDano(t, j,dano);
			}
			else {
				t.defBloqueioDano(oponente, false);
			}
		}
		else {
			tracoCampeao.atacarInimigo(t, j, this, inimigo);
		}
		if(atacou) {
			if(tipoEvolucao == TipoEvolucao.Atacar) {
				andamentoCondicao += 1;
			}
			else if(tipoEvolucao ==  TipoEvolucao.Matar) {
				int vidaInimigo = inimigo.verVidaTotal();
				if(vidaInimigo<=0) {
					if(inimigo instanceof Seguidores) {
						andamentoCondicao+=1;
					}
					ArrayList<Efeito> efeitos = verEfeitos();
					Iterator<Efeito> it = efeitos.iterator();
					while(it.hasNext()) {
						Efeito e = it.next();
						if(e.verTipo() == TipoEfeito.Destruido) {
							e.aplicarEfeitos(t, j);
						}
					}
				}
			}
			else if(tipoEvolucao == TipoEvolucao.DarDano) {
				andamentoCondicao += verDanoAtual();
			}
		}
		evoluir();
	}
	
	@Override 
	public void sofrerDano(Tabuleiro t,Jogador j,int dano){
		super.sofrerDano(t,j,dano);
		if(tipoEvolucao == TipoEvolucao.SofrerDano) {
			andamentoCondicao+= dano;
		}
		ArrayList<Efeito> efeitos = verEfeitos();
		if(verVidaAtual()<=0) {
			Iterator<Efeito> it = efeitos.iterator();
			while(it.hasNext()) {
				Efeito e = it.next();
				if(e.verTipo() == TipoEfeito.Destruido) {
					e.aplicarEfeitos(t, j);
				}
			}
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
		if(verificaEvolucao() && !evoluido) {
			buffEvolucao.evoluir(this);
			evoluido = true;
		}
	}
	
	
	

}
