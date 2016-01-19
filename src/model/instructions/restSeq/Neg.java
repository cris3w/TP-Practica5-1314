package model.instructions.restSeq;

import model.CPU;
import model.instructions.Instruction;

public class Neg extends RestSeq {
	
	protected void executeAux(CPU cpu) {
		cpu.push(0);
		int aux1 = cpu.pop();
		int aux2 = cpu.pop();
		int result = aux1 - aux2;
		cpu.push(result);
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "NEG";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("NEG")) ins = new Neg();
		else ins = null;
		return ins;
	}
}
