package ProjetoFinal.Efeitos;

import java.util.ArrayList;
import java.util.Scanner;

import ProjetoFinal.Carta.Carta;
import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.ItensAdicionais.TipoEfeito;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.Tabuleiro;

public class AtacarDiretamente implements Efeito{
	
	private TipoEfeito tipo;
	
	public AtacarDiretamente() {
		tipo = TipoEfeito.Evocado;
	}

	@Override
	public void aplicarEfeitos(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		int indice = coletarEntrada();
		while(indice >= t.encontraCartasEvocadas(jogador).size()) {
			indice = coletarEntrada();
		}
		ArrayList<Carta> cartasEmCampo = t.encontraCartasEvocadas(jogador);
		Seguidores card = (Seguidores)cartasEmCampo.get(indice);
		Jogador inimigo = t.verOponente(jogador);
		card.atacarInimigo(t,inimigo);
	}
	
	
	private int coletarEntrada() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha o indice de carta em campo para receber um bonus");
		int resposta = scan.nextInt();
		return resposta;
	}

	@Override
	public TipoEfeito verTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	@Override
	public void removerEfeitoAplicado(Tabuleiro t, Jogador jogador) {
		// TODO Auto-generated method stub
		
	}
}
