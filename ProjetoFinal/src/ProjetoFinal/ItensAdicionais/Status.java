package ProjetoFinal.ItensAdicionais;

public class Status {
	private int custoMana;
	private int pontosAtaque;
	private int pontosDefesa;
	
	public Status(int mana) {
		custoMana = mana;
		pontosAtaque = 0;
		pontosDefesa = 0;
	}
	
	public Status(int mana, int ataque) {
		custoMana = mana;
		pontosAtaque = ataque;
		pontosDefesa = 0;
	}
	
	public Status(int mana, int ataque, int defesa) {
		custoMana = mana;
		pontosAtaque = ataque;
		pontosDefesa = defesa;
	}
	
	public int verMana() {
		return custoMana;
	}
	
	public int verAtaque() {
		return pontosAtaque;
	}
	
	public int verDefesa() {
		return pontosDefesa;
	}
	
	public void subtrairVida(int valor) {
		pontosDefesa = pontosDefesa - valor;
		if (pontosDefesa<0) {
			pontosDefesa = 0;
		}
	}
}
