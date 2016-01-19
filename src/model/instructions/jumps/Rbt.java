package model.instructions.jumps;

import exceptions.ExceptionWrongDirection;
import model.CPU;
import model.instructions.Instruction;

public class Rbt extends ConditionalJumps {
	
	/**
	 * Constructora por defecto.
	 */
	public Rbt() {
		this.n = 1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n son las unidades que se incrementa el contador.
	 */
	public Rbt(int n) {
		this.n = n;
	}
	
	protected boolean check(int cima) {
		return cima != 0;
	}

	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "RBT " + this.n;
	}
	
	public void execute(CPU cpu) throws ExceptionWrongDirection {
		int cima = cpu.pop(); 
		if (this.check(cima)) {
			if (!cpu.abortComputation()) {
				int dir = cpu.getProgramCounter() + this.n;
				if (dir > 0 && dir <= /*cpu.getSizeStack())*/cpu.getSizeProgram()){
					cpu.setProgramCounter(dir);
				} else throw new ExceptionWrongDirection("Error ejecutando " + cpu.getInstruction().toString() + ": dirección incorrecta (" + dir + ")");
			}
			else {
				cpu.exit();
			}
		}
		else {
			cpu.increaseProgramCounter();
		}
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("RBT")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Rbt(this.n);
			} else ins = null;
		}
		else ins = null;
		return ins;
	}
}
