package model.instructions.numericCond;

import model.CPU;
import model.instructions.Instruction;

public abstract class NumericCond extends Instruction {
	
	/**
	 * Método que compara la cima y la subcima y aplica la condición correspondiente. 
	 * @param cima es la cima
	 * @param subcima es la subcima
	 * @return si se ha realizado correctamente.
	 */
	abstract protected boolean compare(int cima, int subcima);
	
	public void execute(CPU cpu) {
		int n1 = cpu.pop();
		int n2 = cpu.pop();
		if (compare(n1, n2)) cpu.push(1);
		else cpu.push(0);
		cpu.increaseProgramCounter();
	 }
}
