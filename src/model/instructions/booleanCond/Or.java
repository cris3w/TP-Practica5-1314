package model.instructions.booleanCond;

import model.instructions.Instruction;

public class Or extends BooleanCond {
	
	protected void execute(int cima, int subcima) {
		if (cima == 0 && subcima == 0) this.result = 0;
		else this.result = 1;
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "OR";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("OR")) return new Or();
		else return null;
	}
}
