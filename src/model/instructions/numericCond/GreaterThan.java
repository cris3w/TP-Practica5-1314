package model.instructions.numericCond;

import model.instructions.Instruction;

public class GreaterThan extends NumericCond {
	
	protected boolean compare(int cima, int subcima) {
		return subcima > cima;
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "GT";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("GT")) return new GreaterThan();
		else return null;
	}
}
