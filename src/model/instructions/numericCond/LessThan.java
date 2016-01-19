package model.instructions.numericCond;

import model.instructions.Instruction;

public class LessThan extends NumericCond {
	
	protected boolean compare(int cima, int subcima) {
		return subcima < cima;
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "LT";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LT")) return new LessThan();
		else return null;
	}
}
