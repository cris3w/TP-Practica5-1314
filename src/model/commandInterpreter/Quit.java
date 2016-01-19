package model.commandInterpreter;


public class Quit extends CommandInterpreter {

	public void executeCommand() {
		cpu.exit();
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual del comando.
	 */
	public String toString() {
		return "QUIT";
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 1 && s[0].equalsIgnoreCase("QUIT")) inter = new Quit();
		else inter = null;
		return inter;
	}
}
