package model.instructions.jumps;

import model.instructions.Instruction;

public class BranchIfFalse extends ConditionalJumps {
	
	/**
	 * Constructora por defecto.
	 */
	public BranchIfFalse() {
		this.n = -1;
	}
	
	/**
	 * Constructora con un par�metro.
	 * @param n es la instrucci�n a la que tiene que saltar.
	 */
	public BranchIfFalse(int n) {
		this.n = n;
	}
	
	protected boolean check(int cima) {
		return cima == 0;
	}

	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "BF " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("BF")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new BranchIfFalse(this.n);
			} else ins = null;
		}
		else ins = null;
		return ins;
	}
}
