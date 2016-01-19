package main;

import model.commandInterpreter.CommandInterpreter;
import model.commandInterpreter.Pop;
import model.commandInterpreter.Push;
import model.commandInterpreter.Quit;
import model.commandInterpreter.Run;
import model.commandInterpreter.Step;
import model.commandInterpreter.Steps;
import model.commandInterpreter.Write;

public class ParserCommand {
	
	/**
	 * Método que analiza la orden introducida por el usuario y genera el comando correspondiente.
	 * @param s es la orden introducida
	 * @return el comando correspondiente o null en caso de error
	 */
	public static CommandInterpreter readCommandInstruction(String s) {
		CommandInterpreter inter = null;
		boolean stop = false;
		String palabra[] = s.split(" ");
		int i = 0;
		while (i < ParserCommand.commandInterpreterSet.length && !stop) {
			inter = ParserCommand.commandInterpreterSet[i].parse(palabra);
			if (inter != null) stop = true;
			else i++;
		}
		return inter;
	}
	
	private static CommandInterpreter commandInterpreterSet[] = {
		new Run(), new Step(), new Steps(), new Quit(), new Push(), new Pop(), new Write()
	};
}
