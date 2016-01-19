package view;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CPUObserver;
import model.Memory;
import model.MemoryObserver;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.StackObserver;
import model.instructions.Instruction;

@SuppressWarnings("serial")
public class StateBarPanel extends JPanel implements CPUObserver, StackObserver<Integer>, MemoryObserver<Integer> {
	
	private JLabel instrLabel;
	private JCheckBox memoryBox, stackBox;
	private int numInstr;
	
	public StateBarPanel(Observable<CPUObserver> cpu, Observable<StackObserver<Integer>> stack, 
	Observable<MemoryObserver<Integer>> memory)  { 
		initGUI();
		cpu.addObserver(this);
		stack.addObserver(this);
		memory.addObserver(this);
	}
	
	private void initGUI() {
		this.instrLabel = new JLabel("Num. instrucciones ejecutadas:  0");
		this.add(instrLabel, BorderLayout.WEST);
		this.memoryBox = new JCheckBox("Memoria modificada");
		this.add(memoryBox, BorderLayout.CENTER);
		memoryBox.setEnabled(false);
		this.stackBox = new JCheckBox("Pila modificada");
		this.add(stackBox, BorderLayout.EAST);
		stackBox.setEnabled(false);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
		this.memoryBox.setSelected(false);
		this.stackBox.setSelected(false);
	}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {
		this.numInstr++;
		this.instrLabel.setText("Num. instrucciones ejecutadas:  " + this.numInstr);
	}

	@Override
	public void onStartRun() {}

	@Override
	public void onEndRun() {}

	@Override
	public void onError(String msg) {}

	@Override
	public void onHalt() {}

	@Override
	public void onReset(ProgramMV program) {}

	@Override
	public void onWrite(int index, Integer value) {
		this.memoryBox.setSelected(true);
	}

	@Override
	public void onMemReset() {}

	@Override
	public void onPush(Integer value) {
		this.stackBox.setSelected(true);
	}

	@Override
	public void onPop(Integer value) {
		this.stackBox.setSelected(true);
	}

	@Override
	public void onStackReset() {}
}
