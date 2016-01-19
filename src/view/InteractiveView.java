package view;

import model.CPUObserver;
import model.Memory;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.instructions.Instruction;

public class InteractiveView implements CPUObserver {
	
	public InteractiveView(Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
		System.out.println("Comienza la ejecucion de " + instr.toString());
	}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {
		System.out.println("El estado de la maquina tras ejecutar la instruccion es: " + 
		System.getProperty("line.separator") + memory.toString() + stack.toString());
	}

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
