package model.instructions.restSeq;

import exceptions.ExceptionWrongDirection;
import model.CPU;
import model.instructions.Instruction;

public class Load extends RestSeq {
	
	private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Load() {
		this.n = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es la posición de memoria 
	 * de la cual se quiere apilar el valor que contiene en la pila.
	 */
	public Load(int n) {
		this.n = n;
	}
	
	protected void executeAux(CPU cpu) throws ExceptionWrongDirection {
		int value = cpu.getValueMemory(this.n);
		cpu.push(value);
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "LOAD " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Load(this.n);
			}
			else ins = null;
		}
		else ins = null;
		return ins;
	}
}
