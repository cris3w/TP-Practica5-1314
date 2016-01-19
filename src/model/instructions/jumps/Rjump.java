package model.instructions.jumps;

import model.CPU;
import model.instructions.Instruction;

public class Rjump extends Instruction {
	
	private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Rjump() {
		this.n = 1;
	}
	
	/**
	 * Constructora con un par�metro.
	 * @param n son las unidades que se incrementa el contador.
	 */
	public Rjump(int n) {
		this.n = n;
	}
	
	public void execute(CPU cpu) {
		if (!cpu.abortComputation()) {
			int dir = cpu.getProgramCounter() + this.n;
			if (dir > 0 && dir <= cpu.getSizeProgram()) {
				cpu.setProgramCounter(dir);
			} else cpu.setProgramCounter(cpu.getSizeProgram());
		}
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "RJUMP " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("RJUMP")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Rjump(this.n);
			}
			else ins = null;
		}
		else ins = null;
		return ins;
	}
}