package model.instructions.arithmetics;

import exceptions.ExceptionDivisionByZero;
import model.instructions.Instruction;

public class Div extends Arithmetics{

	protected void execute(int n1, int n2) throws ExceptionDivisionByZero {
		if (n1 == 0) throw new ExceptionDivisionByZero("Divisi�n por cero");
		this.result = n2 / n1;
	}
	
	/**
	 * M�todo encargado de devolver la representaci�n textual de la instrucci�n.
	 */
	public String toString() {
		return "DIV";
	}
	
	public Instruction parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("DIV")) return new Div();
		else return null;
	}
}
