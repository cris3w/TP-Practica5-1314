package model.instructions.restSeq;

import java.io.IOException;

import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import model.CPU;
import model.instructions.Instruction;

abstract public class RestSeq extends Instruction {
	
	/**
	 * M�todo que realiza la operaci�n correspondiente a la instrucci�n.
	 * @param cpu es la CPU
	 * @return si se ha realizado correctamente.
	 */
	abstract protected void executeAux(CPU cpu) throws IOException;
	
	public void execute(CPU cpu) throws ExceptionMissingOperand, ExceptionWrongDirection, IOException {
		this.executeAux(cpu);
		cpu.increaseProgramCounter();
	}
}
