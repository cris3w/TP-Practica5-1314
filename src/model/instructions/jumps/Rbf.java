package model.instructions.jumps;

import exceptions.ExceptionWrongDirection;
import model.CPU;
import model.instructions.Instruction;

public class Rbf extends ConditionalJumps {
	
	/**
	 * Constructora por defecto.
	 */
	public Rbf() {
		this.n = 1;
	}
	
	/**
	 * Constructora con un par�metro.
	 * @param n son las unidades que se incrementa el contador.
	 */
	public Rbf(int n) {
		this.n = n;
	}
	
	protected boolean check(int cima) {
		return cima == 0;
	}

	public void execute(CPU cpu) throws ExceptionWrongDirection {
		int cima = cpu.pop(); 
		if (this.check(cima)) {
			if (!cpu.abortComputation()) {
				int dir = cpu.getProgramCounter() + this.n;
				if (dir > 0 && dir <= /*cpu.getSizeStack())*/cpu.getSizeProgram()) {
					cpu.setProgramCounter(dir);
				} else throw new ExceptionWrongDirection("Error ejecutando " + cpu.getInstruction().toString() + ": direcci�n incorrecta (" + dir + ")");
			}
			else {
				cpu.exit();
			}
		}
		else {
			cpu.increaseProgramCounter();
		}
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "RBF " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("RBF")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Rbf(this.n);
			} else ins = null;
		}
		else ins = null;
		return ins;
	}
}
