package model.instructions.numericCond;

import model.instructions.Instruction;

public class LessOrEqual extends NumericCond {

	protected boolean compare(int cima, int subcima) {
		return subcima <= cima;
	}

	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "LE";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LE")) return new LessOrEqual();
		else return null;
	}
}
