package model.commandInterpreter;


public class Write extends CommandInterpreter {

	private int value;
	private int pos;
	
	/**
	 * Constructora por defecto.
	 */
	public Write() {
		this.value = -1;
		this.pos = -1;
	}
	
	/**
	 * Constructora con un parámetro.
	 * @param n es el argumento del comando
	 */
	public Write(int value, int pos) {
		this.value = value;
		this.pos = pos;
	}
	
	public void executeCommand() {
		boolean correct = false;
		System.out.println("Comienza la ejecucion de " + this.toString());
		if (cpu.getSizeStack() > 0) {
			cpu.setValueMemory(this.pos, this.value);
			correct = true;
		}
		if (correct) printStateMachine();
		else if (!correct && !cpu.isFinished()) System.out.println("Error en la ejecucion de la instruccion");
	}
	
	/**
	 * Método encargado de devolver la representación textual del comando.
	 */
	public String toString() {
		return "WRITE " + this.pos + " " + this.value;
	}
	
	public CommandInterpreter parse(String[] s) {
		CommandInterpreter inter;
		if (s.length == 3 && s[0].equalsIgnoreCase("WRITE")) {
			if (super.isNumber(s[1]) && super.isNumber(s[2])) {
				this.pos = Integer.parseInt(s[1]);
				this.value = Integer.parseInt(s[2]);
				inter = new Write(this.value, this.pos);
			} else inter = null;
		}
		else inter = null;
		return inter;
	}
}
