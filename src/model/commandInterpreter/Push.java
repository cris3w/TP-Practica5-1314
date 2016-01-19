package model.commandInterpreter;


public class Push extends CommandInterpreter {
	
private int n;
	
	/**
	 * Constructora por defecto.
	 */
	public Push() { 
		this.n = -1;
	}
	
	/**
	 * Constructora con un par�metro.
	 * @param n es el argumento del comando
	 */
	public Push(int n) {
		this.n = n;
	}
	
	public void executeCommand() {
		System.out.println("Comienza la ejecucion de " + this.toString());
		cpu.push(this.n);
		printStateMachine();
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual del comando.
	 */
	public String toString() {
		return "PUSH " + this.n;
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) {
			if (super.isNumber(s[1])) {
				this.n = Integer.parseInt(s[1]);
				inter = new Push(this.n);
			} else inter = null;
		}
		else inter = null;
		return inter;
	}

}
