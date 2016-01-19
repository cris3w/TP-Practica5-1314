package model.instructions.booleanCond;

import model.instructions.Instruction;

public class And extends BooleanCond {
	
	protected void execute(int cima, int subcima) {
		if (cima != 0 && subcima != 0) this.result = 1;
		else this.result = 0;
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "AND";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("AND")) return new And();
		else return null;
	}
}
