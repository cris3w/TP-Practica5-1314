package model.instructions.restSeq;

import java.io.IOException;

import model.CPU;
import model.instructions.Instruction;

public class Out extends RestSeq {

	protected void executeAux(CPU cpu) throws IOException {
		int top = cpu.getTopStack();
		cpu.pop();
		cpu.write(top);
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "OUT";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("OUT")) ins = new Out();
		else ins = null;
		return ins;
	}
}
