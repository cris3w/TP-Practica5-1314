package model.instructions.jumps;

import model.CPU;
import model.instructions.Instruction;

public class JumpInd extends Instruction {

	public void execute(CPU cpu) {
		int top = cpu.getTopStack();
		if (top > 0 && top <= /*cpu.getSizeStack())*/cpu.getSizeProgram()) {
			cpu.pop();
			cpu.setProgramCounter(top);
		} else cpu.setProgramCounter(cpu.getSizeProgram());
	}

	public String toString() {
		return "JUMPIND";
	}
	
	public Instruction parse(String[] s) {
		Instruction ins;
		if (s.length == 1 && s[0].equalsIgnoreCase("JUMPIND")) ins = new JumpInd();
		else ins = null;
		return ins;
	}
}
