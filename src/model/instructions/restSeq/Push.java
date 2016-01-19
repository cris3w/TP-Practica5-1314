package model.instructions.restSeq;

import model.CPU;
import model.instructions.Instruction;

public class Push extends RestSeq {
	
	private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Push() {
		this.n = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es el valor que se quiere apilar.
	 */
	public Push(int n) {
		this.n = n;
	}
	
	protected void executeAux(CPU cpu) {		
		cpu.push(this.n);		
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "PUSH " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Push(this.n);
			}
			else ins = null;
		}
		else ins = null;
		return ins;
	}
}
