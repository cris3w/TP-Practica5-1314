package model.instructions.booleanCond;

import model.CPU;
import model.instructions.Instruction;

abstract public class BooleanCond extends Instruction {
	protected int result;
	
	/**
	 * M�todo que realiza la operaci�n correspondiente a la instrucci�n.
	 * @param n1 es uno de los operandos
	 * @param n2 es otro de los operandos
	 * @return el resultado de la operaci�n
	 */
	abstract protected void execute(int n1, int n2);
	
	public void execute(CPU cpu) {
		int n1 = cpu.pop();
		int n2 = cpu.pop();
		this.execute(n1, n2);
		cpu.push(this.result);
		cpu.increaseProgramCounter();
	}
}
