package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controler.Controler;
import controler.GUIControler;
import model.CPUObserver;
import model.Memory;
import model.MemoryObserver;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.StackObserver;
import model.instructions.Instruction;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements CPUObserver {

	private Observable<CPUObserver> cpu;
	private ToolBarPanel toolBar;
	private StateBarPanel stateBar;
	private ProgramPanel program;
	private Observable<StackObserver<Integer>> stack;
	private Observable<MemoryObserver<Integer>> memory;
	private InputPanel input;
	private OutputPanel output;
	private Controler ctrl;
	
	public MainWindow(GUIControler guiCtrl, Observable<CPUObserver> cpu, 
	Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory) throws IOException {
		super("Virtual Machine");
		super.setSize(900, 700);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
					JFrame emergente = new JFrame();
					int i = JOptionPane.showOptionDialog(emergente, "¿Esta seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (i == 1) {
						
					} else {
						System.exit(0);
					}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
			
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.cpu = cpu;
		this.stack = stack;
		this.memory = memory;
		this.ctrl = guiCtrl;
		initGUI();
		cpu.addObserver(this);
	}
	
	private void initGUI() throws IOException {
		this.toolBar = new ToolBarPanel(this.ctrl, this.cpu);
		this.stateBar = new StateBarPanel(this.cpu, this.stack, this.memory);
		this.program = new ProgramPanel(this.ctrl, this.cpu);
		StackPanel stackPanel = new StackPanel(this.ctrl, this.stack, this.cpu);
		MemoryPanel memoryPanel = new MemoryPanel(this.ctrl, this.memory, this.cpu);
		this.input = new InputPanel(this.ctrl);
		this.output = new OutputPanel(this.ctrl);
		
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.2;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		mainPanel.add(program, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weightx = 0;
		mainPanel.add(toolBar, c);
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(stateBar, c);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.8;		
		c.weighty = 0.7;		
		mainPanel.add(addNorthPanel(stackPanel, memoryPanel), c);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weighty = 0.2;
		mainPanel.add(addSouthPanel(), c);
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	
	// StackPanel y MemoryPanel
	JPanel addNorthPanel(StackPanel stackPanel, MemoryPanel memoryPanel) {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(stackPanel, c);
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(memoryPanel, c);
		return mainPanel;
	}
			
	// Input y Output
	JPanel addSouthPanel() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy= 0;
		mainPanel.add(input, c);
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(output, c);
		return mainPanel;
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
		JFrame emergente = new JFrame();
		JOptionPane.showMessageDialog(emergente, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void onHalt() {}

	@Override
	public void onReset(ProgramMV program) {}
}
