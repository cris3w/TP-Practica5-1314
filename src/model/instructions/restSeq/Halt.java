package model.instructions.restSeq;

import model.CPU;
import model.instructions.Instruction;

public class Halt extends RestSeq{

	protected void executeAux(CPU cpu) {
		cpu.exit();
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "HALT";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("HALT")) ins = new Halt();
		else ins = null;
		return ins;
	}
}
