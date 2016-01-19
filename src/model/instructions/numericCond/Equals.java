package model.instructions.numericCond;

import model.instructions.Instruction;

public class Equals extends NumericCond {

	protected boolean compare(int cima, int subcima) {
		return cima == subcima;
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "EQ";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("EQ")) return new Equals();
		else return null;
	}
}
