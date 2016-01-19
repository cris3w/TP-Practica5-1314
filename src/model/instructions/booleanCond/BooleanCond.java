package model.instructions.booleanCond;

import model.CPU;
import model.instructions.Instruction;

abstract public class BooleanCond extends Instruction {
	protected int result;
	
	/**
	 * Método que realiza la operación correspondiente a la instrucción.
	 * @param n1 es uno de los operandos
	 * @param n2 es otro de los operandos
	 * @return el resultado de la operación
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
