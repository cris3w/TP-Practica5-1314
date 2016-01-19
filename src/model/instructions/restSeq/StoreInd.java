package model.instructions.restSeq;

import exceptions.ExceptionMissingOperand;
import model.CPU;
import model.instructions.Instruction;

public class StoreInd extends RestSeq {
	
	protected void executeAux(CPU cpu) throws ExceptionMissingOperand {
		int value = cpu.getTopStack();
		cpu.pop();
		int pos = cpu.getTopStack();
		cpu.setValueMemory(pos, value);
		cpu.pop();
	}

	public String toString() {
		return "STOREIND";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("STOREIND")) ins = new StoreInd();
		else ins = null;
		return ins;
	}
}
