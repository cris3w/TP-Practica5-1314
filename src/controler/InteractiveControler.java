package controler;

import java.io.IOException;
import java.util.Scanner;

import exceptions.ExceptionDivisionByZero;
import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import main.ParserCommand;
import model.CPU;
import model.commandInterpreter.CommandInterpreter;

public class InteractiveControler extends Controler {
	
	public InteractiveControler(CPU cpu) {
		super(cpu);
	}

	// Bucle donde en cada iteración se pide un comando (STEP, RUN, PUSH, etc.)
	// al usuario y el método que corresponde (de Controler).
	// No muestra nada en la consola, la vista se encarga de esto
	public void start() { 
		Scanner line = new Scanner(System.in);
		String s;
		do {
				System.out.print(">");
				s = line.nextLine();
				CommandInterpreter instrCommand = ParserCommand.readCommandInstruction(s);
				try {
					if (instrCommand == null) System.out.println("No te entiendo");
					else instrCommand.executeCommand();
				} catch (ExceptionMissingOperand e) {
				} catch (ExceptionDivisionByZero e) {	
				} catch (ExceptionWrongDirection e) {
				} catch (IOException e) {	
				}
		} while (!s.equalsIgnoreCase("QUIT"));
		line.close();
	}
}
