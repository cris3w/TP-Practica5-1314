package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controler.Controler;
import model.CPUObserver;
import model.Memory;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.instructions.Instruction;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ToolBarPanel extends JPanel implements CPUObserver {
	
	private Controler ctrl;
	private JButton stepButton, runButton, quitButton;
	
	ToolBarPanel(Controler ctrl, Observable<CPUObserver> cpu) { 
		this.ctrl = ctrl;
		initGUI();
		cpu.addObserver(this);
	}
	
	private void initGUI() {
		// STEP BUTTON
		this.stepButton = new JButton();
		this.stepButton.setIcon(createImageIcon("step.png"));
		this.stepButton.setToolTipText("Step");
		this.add(stepButton);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.step();
			}
		});
		
		// RUN BUTTON
		this.runButton = new JButton();
		this.runButton.setIcon(createImageIcon("run.png"));
		this.runButton.setToolTipText("Run");
		this.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.run();
			}
		});
		
		// QUIT BUTTON
		this.quitButton = new JButton();
		this.quitButton.setIcon(createImageIcon("exit.png"));
		this.quitButton.setToolTipText("Exit");
		this.add(quitButton);
		this.quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame emergente = new JFrame();
				int i = JOptionPane.showOptionDialog(emergente, "¿Esta seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (i == 1) {
					
				} else{
					ctrl.quit();
				}
			}
		});
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {}

	@Override
	public void onStartRun() {
		runButton.setEnabled(false);
		stepButton.setEnabled(false);
	}

	@Override
	public void onEndRun() {
		runButton.setEnabled(false);
		stepButton.setEnabled(false);
	}

	@Override
	public void onError(String msg) {
		runButton.setEnabled(true);
		stepButton.setEnabled(true);
	}

	@Override
	public void onHalt() {}

	@Override
	public void onReset(ProgramMV program) {}

	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainWindow.class.getResource(path);
		if (imgURL != null) return new ImageIcon(imgURL);
		return null;
	}
}
