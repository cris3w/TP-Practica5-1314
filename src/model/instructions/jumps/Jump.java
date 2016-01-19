package model.instructions.jumps;

import exceptions.ExceptionWrongDirection;
import model.CPU;
import model.instructions.Instruction;

public class Jump extends Instruction {
	
	private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Jump() {
		this.n = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es la instrucción a la que tiene que saltar.
	 */
	public Jump(int n) {
		this.n = n;
	}
	
	public void execute(CPU cpu) throws ExceptionWrongDirection {
		if (this.n >= 0 && this.n <= cpu.getSizeProgram()) {
			cpu.setProgramCounter(this.n);
		} else cpu.setProgramCounter(cpu.getSizeProgram());
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "JUMP " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("JUMP")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Jump(this.n);
			}
			else ins = null;
		}
		else ins = null;
		return ins;
	}
}
