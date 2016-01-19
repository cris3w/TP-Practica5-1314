package model.instructions.arithmetics;

import model.instructions.Instruction;

public class Add extends Arithmetics{

	protected void execute(int n1, int n2) {
		this.result = n1 + n2;
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "ADD";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("ADD")) return new Add();
		else return null;
	}
}
