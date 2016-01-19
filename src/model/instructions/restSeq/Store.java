package model.instructions.restSeq;

import model.CPU;
import model.instructions.Instruction;

public class Store extends RestSeq {
	
	private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Store() {
		this.n = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es la posición de memoria en la que se quiere almacenar el valor de la cima.
	 */
	public Store(int n) {
		this.n = n;
	}
	
	protected void executeAux(CPU cpu) {
		cpu.setValueMemory(this.n);
		cpu.pop();
	}
	
	/**
	 * Método encargado de devolver la representación textual de la instrucción.
	 */
	public String toString() {
		return "STORE " + this.n;
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 2 && s[0].equalsIgnoreCase("STORE")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				ins = new Store(this.n);
			}
			else ins = null;
		}
		else ins = null;
		return ins;
	}
}
