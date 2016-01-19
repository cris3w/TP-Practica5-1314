package model.commandInterpreter;

import java.io.IOException;

import exceptions.ExceptionDivisionByZero;
import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;

public class Step extends CommandInterpreter {
	
	public void executeCommand() throws ExceptionDivisionByZero, ExceptionWrongDirection, ExceptionMissingOperand, IOException  {
		cpu.step();
	}
	
	/**
	 * Método encargado de devolver la representación textual del comando.
	 */
	public String toString() {
		return "STEP";
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 1 && s[0].equalsIgnoreCase("STEP")) inter = new Step();
		else inter = null;
		return inter;
	}
}
