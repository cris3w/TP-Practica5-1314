package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controler.Controler;
import model.CPUObserver;
import model.Memory;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.instructions.Instruction;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements CPUObserver  {
	
	private Controler ctrl;
	private JTextArea programTextArea;
	private ProgramMV program;
	private int pc;
	
	ProgramPanel(Controler ctrl, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		this.initGUI();
		cpu.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Program"));
		programTextArea = new JTextArea(20, 15);
		programTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		programTextArea.setEditable(false);
		this.add(new JScrollPane(programTextArea));
		
		this.program = ctrl.getProgram();
		this.pc = 0;
		this.showProgram();
	}
	
	private void showProgram() {
		String progText = "";
		for (int i = 0; i < this.program.getSizeProgram(); i++) {
			if (i != this.pc) progText = progText + "  " + this.program.get(i) + "\n";
			else progText = progText + "* " + this.program.get(i) + "\n";
		}
		programTextArea.setText(progText);
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {
		this.pc = pc;
		this.showProgram();
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
	public void onReset(ProgramMV program) {
		this.pc = 0;
		this.showProgram();
	}
}
