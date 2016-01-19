package model.instructions.restSeq;


import java.io.IOException;

import model.CPU;
import model.instructions.Instruction;

public class In extends RestSeq {
	
	protected void executeAux(CPU cpu) throws IOException {
		int n = cpu.read();
		cpu.push(n);
	}

	public String toString() {
		return "IN ";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("IN")) ins = new In();
		else ins = null;
		return ins;
	}
}
