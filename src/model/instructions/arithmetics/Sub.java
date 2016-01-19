package model.instructions.arithmetics;

import model.instructions.Instruction;

public class Sub extends Arithmetics {
	
	protected void execute(int n1, int n2) {
		this.result = n2 - n1;
	}
		
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "SUB";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("SUB")) return new Sub();
		else return null;
	}
}
