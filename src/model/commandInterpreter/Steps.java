package model.commandInterpreter;

import java.io.IOException;

import exceptions.ExceptionWrongDirection;

public class Steps extends Step {
	
	private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Steps() { 
		this.n = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es el argumento del comando
	 */
	public Steps(int n) {
		this.n = n;
	}
	
	public void executeCommand() throws ExceptionWrongDirection, IOException {
		cpu.stepn(n);
	}
	
	/**
	 * Método encargado de devolver la representación textual del comando.
	 */
	public String toString() {
		return "STEP" + this.n;
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 2 && s[0].equalsIgnoreCase("STEP")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				inter = new Steps(this.n);
			} else inter = null;
		}
		else inter = null;
		return inter;
	}
}
