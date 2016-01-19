package model.commandInterpreter;

import java.io.IOException;

import exceptions.ExceptionWrongDirection;

public class Run extends Step {
	
	public void executeCommand() throws ExceptionWrongDirection, IOException {
		cpu.run();
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual del comando.
	 */
	public String toString() {
		return "RUN";
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 1 && s[0].equalsIgnoreCase("RUN")) inter = new Run();
		else inter = null;
		return inter;
	}
}
