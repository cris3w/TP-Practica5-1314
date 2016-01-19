package model.instructions.jumps;

import model.CPU;
import model.instructions.Instruction;

abstract public class ConditionalJumps extends Instruction {
	
	protected int n;
	
	/**
	 * Método que comprueba la cima y realiza el salto correspondiente.
	 * @param cima es la cima
	 * @return si se ha realizado el salto correctamente
	 */
	abstract protected boolean check(int cima);
	
	public void execute(CPU cpu) {
		int cima = cpu.pop(); 
		if (this.check(cima)) {
			if (this.n >= 0 && this.n <= cpu.getSizeProgram()) {
				cpu.setProgramCounter(this.n);
			} else cpu.setProgramCounter(cpu.getSizeProgram());
		} else cpu.increaseProgramCounter();
	}
}
