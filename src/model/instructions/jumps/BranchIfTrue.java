package model.instructions.jumps;

import model.instructions.Instruction;

public class BranchIfTrue extends ConditionalJumps {
	
	/**
	 * Constructora por defecto.
	 */
	public BranchIfTrue() {
		this.n = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es la instrucción a la que tiene que saltar.
	 */
	public BranchIfTrue(int n) {
		this.n = n;
	}
	
	protected boolean check(int cima) {
		return cima != 0;
	}

	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "BT " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("BT")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new BranchIfTrue(this.n);
			} else ins = null;
		}
		else ins = null;
		return ins;
	}
}
