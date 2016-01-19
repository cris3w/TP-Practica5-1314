package model.instructions.restSeq;

import exceptions.ExceptionMissingOperand;
import model.CPU;
import model.instructions.Instruction;

public class Dup extends RestSeq {
	
	protected void executeAux(CPU cpu) throws ExceptionMissingOperand {
		if (cpu.getSizeStack() > 0) {
			cpu.push(cpu.getTopStack());
		} else throw new ExceptionMissingOperand("Error ejecutando " + cpu.getInstruction().toString() + ": faltan operandos en pila (hay " + cpu.getSizeStack() + ")");
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "DUP";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("DUP")) ins = new Dup();
		else ins = null;
		return ins;
	}
}
