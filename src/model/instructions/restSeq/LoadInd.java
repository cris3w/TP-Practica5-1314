package model.instructions.restSeq;

import exceptions.ExceptionMissingOperand;
import model.CPU;
import model.instructions.Instruction;

public class LoadInd extends RestSeq {

	protected void executeAux(CPU cpu) throws ExceptionMissingOperand {
		if (cpu.getSizeStack() > 0) {
			int top = cpu.getTopStack();
			int value = cpu.getValueMemory(top);
			cpu.push(value);
		} else throw new ExceptionMissingOperand("Error ejecutando " + cpu.getInstruction() + ": faltan operandos en la pila");
	}

	public String toString() {
		return "LOADIND";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("LOADIND")) ins = new LoadInd();
		else ins = null;
		return ins;
	}
}
