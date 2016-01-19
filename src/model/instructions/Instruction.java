
package model.instructions;

import java.io.IOException;

import exceptions.ExceptionWrongDirection;
import model.CPU;

abstract public class Instruction {

	/**
	 * Método que ejecuta la instrucción correspondiente.
	 * @return si la instrucción se ha ejecutado correctamente
	 */
	abstract public void execute(CPU cpu) throws ExceptionWrongDirection, IOException;
	
	/**
	 * Método que analiza la orden introducida por el usuario 
	 * y genera la instrucción correspondiente.
	 * @param s es la orden introducida
	 * @return la instrucción correspondiente
	 */
	abstract public Instruction parse(String[] s);
	
	/**
	 * Método que comprueba si la cadena de caracteres introducida es un número.
	 * @param s es la cadena de caracteres
	 * @return si es un número
	 */
	protected static boolean isNumber(String s) {
		boolean number = true;
		if( Character.isDigit(s.charAt(0)) || (s.charAt(0) == '-')) number = true;
		else number = false;
		int i = 1;
		while (i < s.length() && number) {
			if (!Character.isDigit(s.charAt(i))) number = false;
			i++;
		}
		return number;
	}
	
	protected static boolean isCaracter(String s) {
		boolean caracter = true;
		if (Character.isLetter(s.charAt(0)) && s.length() == 1) caracter = true;
		else caracter = false;
		int i = 1;
		while (i < s.length() && caracter) {
			if (!Character.isLetter(s.charAt(i))) caracter = false;
			i++;
		}
		return caracter;
	}
}
