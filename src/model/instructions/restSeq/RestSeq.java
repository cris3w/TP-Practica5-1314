package model.instructions.restSeq;

import java.io.IOException;

import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import model.CPU;
import model.instructions.Instruction;

abstract public class RestSeq extends Instruction {
	
	/**
	 * Método que realiza la operación correspondiente a la instrucción.
	 * @param cpu es la CPU
	 * @return si se ha realizado correctamente.
	 */
	abstract protected void executeAux(CPU cpu) throws IOException;
	
	public void execute(CPU cpu) throws ExceptionMissingOperand, ExceptionWrongDirection, IOException {
		this.executeAux(cpu);
		cpu.increaseProgramCounter();
	}
}
