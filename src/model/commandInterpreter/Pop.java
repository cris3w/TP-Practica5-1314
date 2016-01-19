package model.commandInterpreter;


public class Pop extends CommandInterpreter {
	
	public void executeCommand() {
		System.out.println("Comienza la ejecucion de " + this.toString());
		cpu.pop();
		printStateMachine();
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "POP ";
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 1 && s[0].equalsIgnoreCase("POP")) inter = new Pop();
		else inter = null;
		return inter;
	}
}



