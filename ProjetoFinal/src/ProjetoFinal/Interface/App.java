package ProjetoFinal.Interface;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ProjetoFinal.Carta.Campeoes;
import ProjetoFinal.Carta.Feiticos;
import ProjetoFinal.Carta.Seguidores;
import ProjetoFinal.Deck.Deck;
import ProjetoFinal.Efeitos.AtacarDiretamente;
import ProjetoFinal.Efeitos.CombateImediato;
import ProjetoFinal.Efeitos.ComprarCartaAoSerDestruido;
import ProjetoFinal.Efeitos.DanificarNexus;
import ProjetoFinal.Efeitos.DobrarAtaqueEDefesa;
import ProjetoFinal.Efeitos.EvocarCartaTipoEspecifico;
import ProjetoFinal.Efeitos.MelhorarTodosAliados;
import ProjetoFinal.Efeitos.MelhorarUmAliado;
import ProjetoFinal.Efeitos.RecuperarVidaAliado;
import ProjetoFinal.Efeitos.UmAliadoAtacaTodosInimigos;
import ProjetoFinal.Efeitos.ZerarAtaque;
import ProjetoFinal.Evoluir.AdicionarHabilidade;
import ProjetoFinal.Evoluir.AumentarPoder;
import ProjetoFinal.Evoluir.AumentarVida;
import ProjetoFinal.ItensAdicionais.Status;
import ProjetoFinal.ItensAdicionais.TipoEvolucao;
import ProjetoFinal.Jogador.Jogador;
import ProjetoFinal.Tabuleiro.TabuleiroInterface;
import ProjetoFinal.Tracos.AtaqueDuplo;
import ProjetoFinal.Tracos.Elusivo;
import ProjetoFinal.Tracos.Furia;

public class App {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Game started!");
		System.out.println("");

		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("Legends of Runeterra");
		
		//Status
		Status garenS = new Status(5,5,5);	
		Status tianaS = new Status(8,7,7);
		Status vanguardaS = new Status(4,3,3);
		Status duelistaS = new Status(3,3,2);
		Status defensorS = new Status(2,2,2);
		Status poroS = new Status(2,2,2);
		Status poroDefensorS = new Status(1,2,1);
		Status julgamentoS = new Status(8);
		Status valorRedobradoS = new Status(6);
		Status golpeCerteiroS = new Status(1);
		Status comabateUmAUmS = new Status(2);
		Status furiaS = new Status(0,0,1);
		
		//Efeitos
		AtacarDiretamente atacarDiretamente = new AtacarDiretamente();
		CombateImediato combateImediato = new CombateImediato();
		ComprarCartaAoSerDestruido comprarCartasAoSerDestruido = new ComprarCartaAoSerDestruido();
		DanificarNexus danificarNexus = new DanificarNexus(5);
		DobrarAtaqueEDefesa dobrarAtaqueEDefesa = new DobrarAtaqueEDefesa();
		EvocarCartaTipoEspecifico evocarCartaTipoEspecifico = new EvocarCartaTipoEspecifico("Poro");
		MelhorarTodosAliados melhorarTodosAliados = new MelhorarTodosAliados(1,1);
		MelhorarUmAliado melhorarUmAliado = new MelhorarUmAliado(1,1);
		RecuperarVidaAliado recuperarVidaAliado = new RecuperarVidaAliado();
		UmAliadoAtacaTodosInimigos umAliadoAtacaTodosInimigos = new UmAliadoAtacaTodosInimigos();
		ZerarAtaque zerarAtaque = new ZerarAtaque();
		
		//Traco
		AtaqueDuplo ataqueDuplo = new AtaqueDuplo();
		Elusivo elusivo = new Elusivo();
		Furia furia = new Furia(furiaS);
		
		//Evolucao
		AdicionarHabilidade adicionarHabilidade = new AdicionarHabilidade(elusivo);
		AumentarPoder aumentarPoder = new AumentarPoder(1);
		AumentarVida aumentarVida = new AumentarVida(1);
		
		//Cartas
		Campeoes garen = new Campeoes("Garen", garenS , null, adicionarHabilidade, TipoEvolucao.Atacar, 2);
		Seguidores tiana = new Seguidores("Tiana", tianaS, danificarNexus);
		Seguidores vanguarda = new Seguidores("Vanguarda", vanguardaS, melhorarTodosAliados);
		Seguidores duelista = new Seguidores("Duelista", duelistaS, evocarCartaTipoEspecifico);
		Seguidores defensor = new Seguidores("Defensor", defensorS, furia);
		Seguidores poro = new Seguidores("Poro", poroS);
		Seguidores poroDefensor = new Seguidores("Poro Defensor", poroDefensorS, comprarCartasAoSerDestruido);
		Feiticos julgamento = new Feiticos("Julgamento", julgamentoS, umAliadoAtacaTodosInimigos);
		Feiticos valorRedobrado = new Feiticos("Valor Redobrado", valorRedobradoS, recuperarVidaAliado);
		Feiticos golpeCerteiro = new Feiticos("Golpe Certeiro", golpeCerteiroS, melhorarUmAliado);
		Feiticos combateUmAUm = new Feiticos("Combate um a um", comabateUmAUmS, combateImediato);

		//Decks
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		
		deck1.adicionarCarta(garen);
		deck1.adicionarCarta(tiana);
		deck1.adicionarCarta(vanguarda);
		deck1.adicionarCarta(duelista);
		deck1.adicionarCarta(defensor);
		deck1.adicionarCarta(poro);
		deck1.adicionarCarta(poroDefensor);
		deck1.adicionarCarta(julgamento);
		deck1.adicionarCarta(valorRedobrado);
		deck1.adicionarCarta(golpeCerteiro);
		deck1.adicionarCarta(combateUmAUm);
		
		deck2.adicionarCarta(garen);
		deck2.adicionarCarta(tiana);
		deck2.adicionarCarta(vanguarda);
		deck2.adicionarCarta(duelista);
		deck2.adicionarCarta(defensor);
		deck2.adicionarCarta(poro);
		deck2.adicionarCarta(poroDefensor);
		deck2.adicionarCarta(julgamento);
		deck2.adicionarCarta(valorRedobrado);
		deck2.adicionarCarta(golpeCerteiro);
		deck2.adicionarCarta(combateUmAUm);
		
		Jogador jogador1 = new Jogador("Jogador 1", deck1);
		Jogador jogador2 = new Jogador("Jogador 2", deck2);
		TabuleiroInterface tabuleiro = new TabuleiroInterface(jogador1, jogador2);
		
		tabuleiro.iniciaJogo(shell);
		
		tabuleiro.rodadasJogo(shell);
		
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		System.out.println("Game terminated. Bye!");
	}
}
