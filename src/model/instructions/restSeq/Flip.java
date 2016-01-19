package model.instructions.restSeq;

import model.CPU;
import model.instructions.Instruction;

public class Flip extends RestSeq {
	
	protected void executeAux(CPU cpu) {
		int aux1 = cpu.pop();
		int aux2 = cpu.pop();
		cpu.push(aux1);
		cpu.push(aux2);
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "FLIP";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("FLIP")) ins = new Flip();
		else ins = null;
		return ins;
	}
}
