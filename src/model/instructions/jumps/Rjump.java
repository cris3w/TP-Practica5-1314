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
	 * Constructora con un parámetro.
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
	 * Método encargado de devolver la representación textual de la instrucción.
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