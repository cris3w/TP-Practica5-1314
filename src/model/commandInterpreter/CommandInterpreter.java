package model.commandInterpreter;

import java.io.IOException;

import exceptions.ExceptionDivisionByZero;
import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import model.CPU;

abstract public class CommandInterpreter {
	
	protected static CPU cpu;

	/**
	 * M�todo que configura el int�rprete de comandos.
	 * @param c es la CPU
	 */
	public static void configureCommandInterpreter(CPU c) {
		cpu = c;
	}

	/**
	 * M�todo que ejecuta el comando correspondiente.
	 * @return si el comando se ha ejecutado correctamente  
	 * @throws IOException 
	 */
	abstract public void executeCommand() throws ExceptionDivisionByZero, ExceptionMissingOperand, ExceptionWrongDirection, IOException;

	/**
	 * M�todo encargado de devolver una representaci�n textual del estado de la maquina.
	 */
	public static void printStateMachine() {
		System.out.println(cpu.toString());
	}
	
	/**
	 * M�todo que analiza la orden introducida por el usuario 
	 * y genera el interprete de comandos correspondiente.
	 * @param s es la orden introducida
	 * @return el interprete de comandos
	 */
	abstract public CommandInterpreter parse(String[] s);
	
	/**
	 * M�todo que comprueba si la cadena de caracteres introducida es un n�mero.
	 * @param s es la cadena de caracteres
	 * @return si es un n�mero
	 */
	protected static boolean isNumber(String s) {
		boolean number = true;
		int i = 0;
		while (i < s.length() && number) {
			if (!Character.isDigit(s.charAt(i))) number = false;
			i++;
		}
		return number;
	}
}
