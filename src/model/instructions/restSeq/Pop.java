package model.instructions.restSeq;

import model.CPU;
import model.instructions.Instruction;

public class Pop extends RestSeq {
	
	protected void executeAux(CPU cpu) {
		cpu.pop();
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "POP";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("POP")) ins = new Pop();
		else ins = null;
		return ins;
	}
}
