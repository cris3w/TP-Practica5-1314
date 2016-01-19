package model;

import model.instructions.Instruction;

public interface CPUObserver {
	
	public void onStartInstrExecution(Instruction instr);
	
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory);
	
	public void onStartRun();
	
	public void onEndRun(/*...*/);
	
	public void onError(String msg);
	
	public void onHalt();
	
	public void onReset(ProgramMV program); // OPCIONAL
}
