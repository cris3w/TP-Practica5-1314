package view;

import model.CPUObserver;
import model.Memory;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.instructions.Instruction;

public class BatchView implements CPUObserver {
	
	public BatchView(Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {}

	@Override
	public void onStartRun() {}

	@Override
	public void onEndRun() {}

	@Override
	public void onError(String msg) {
		System.err.println(msg);
	}

	@Override
	public void onHalt() {}

	@Override
	public void onReset(ProgramMV program) {}
}
