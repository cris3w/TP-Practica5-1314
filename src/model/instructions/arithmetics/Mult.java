package model.instructions.arithmetics;

import model.instructions.Instruction;

public class Mult extends Arithmetics {

	protected void execute(int n1, int n2) {
		this.result = n1 * n2;
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "MUL";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("MUL")) return new Mult();
		else return null;
	}
}
