package model.instructions.booleanCond;

import exceptions.ExceptionMissingOperand;
import model.CPU;
import model.instructions.Instruction;

public class Not extends Instruction {

	public void execute(CPU cpu) throws ExceptionMissingOperand {
		if (cpu.getSizeStack() > 0) {
			int n1 = cpu.pop();
			if (n1 == 0) cpu.push(1);
			else cpu.push(0);
			cpu.increaseProgramCounter();
		} else throw new ExceptionMissingOperand("Error ejecutando " + cpu.getInstruction() + ": faltan operandos en la pila (hay " + cpu.getSizeStack() + ")");
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "NOT";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("NOT")) return new Not();
		else return null;
	}
}
