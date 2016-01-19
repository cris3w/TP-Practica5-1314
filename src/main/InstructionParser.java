package main;


import model.instructions.Instruction;
import model.instructions.arithmetics.*;
import model.instructions.booleanCond.*;
import model.instructions.jumps.*;
import model.instructions.numericCond.*;
import model.instructions.restSeq.*;

/**
 * Clase encargada de analizar la entrada del usuario y generar la instrucción correspondiente.
 */
public class InstructionParser {
	
	/**
	 * Método que analiza la orden introducida por el usuario y genera la instrucción correspondiente.
	 * @param line es la orden introducida
	 * @return la instrucción correspondiente o null en caso de error
	 */
	public static Instruction parseProgramInstruction(String line) {
		Instruction ins = null;
		boolean stop = false;
		String palabra[] = line.split(" ");
		int i = 0;
		while (i < InstructionParser.instructionSet.length && !stop) {
			ins = InstructionParser.instructionSet[i].parse(palabra);
			if (ins != null) stop = true;
			else i++;
		}
		return ins;
	}
	
	private static Instruction instructionSet[] = {
		new Add(), new Sub(), new Mult(),
		new Div(), new Dup(), new Flip(), 
		new Halt(), new Load(), new Neg(), 
		new Out(), new Pop(), new Push(), 
		new Store(), new Equals(), new GreaterThan(),
		new LessOrEqual(), new LessThan(), new Jump(),
		new BranchIfFalse(), new BranchIfTrue(), 
		new And(), new Or(), new Not(),
		new Rjump(), new Rbt(), new Rbf(),
		new In(), new JumpInd(), new LoadInd(),
		new StoreInd(), new In()
	};
}

	
