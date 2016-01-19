package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controler.Controler;
import exceptions.ExceptionMissingOperand;
import model.CPUObserver;
import model.Memory;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.StackObserver;
import model.instructions.Instruction;

@SuppressWarnings("serial")
public class StackPanel extends JPanel implements StackObserver<Integer>, CPUObserver, ActionListener {
	
	private Controler ctrl;
	private JList<Integer> stackArea;
	private JTextField valueElem;
	private JLabel valueLabel;
	private JButton pushButton, popButton;
	private DefaultListModel<Integer> model;
	
	StackPanel(Controler ctrl, Observable<StackObserver<Integer>> stack, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		initGUI();
		cpu.addObserver(this);
		stack.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("OperandStack"));
		
		model = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(model);
		stackArea.setFont(new Font("Courier", Font.PLAIN, 14));
		
		JPanel buttonsPanel = new JPanel();
		this.add(buttonsPanel, BorderLayout.PAGE_END);

		valueLabel = new JLabel("Value");
		buttonsPanel.add(valueLabel);
		valueElem = new JTextField(5);
		buttonsPanel.add(valueElem);
		
		pushButton = new JButton("Push");
		pushButton.addActionListener(this);
		buttonsPanel.add(pushButton);
		popButton = new JButton("Pop");
		popButton.addActionListener(this);
		buttonsPanel.add(popButton);
		
		this.add(new JScrollPane(stackArea));
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		String nText = valueElem.getText();
		Integer n = null;
		if (e.getSource() == pushButton) {
			try {
				n = Integer.parseInt(nText);
				ctrl.push(n);
			} catch (NumberFormatException e1) {
				JFrame emergente = new JFrame();
				JOptionPane.showMessageDialog(emergente, "El valor tiene que ser numerico", "Formato erroneo", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == popButton) {
			try{
				ctrl.pop();
			}catch (ExceptionMissingOperand e2){
				JFrame emergente = new JFrame();
				JOptionPane.showMessageDialog(emergente, e2.getMessage(), "Formato erroneo", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {}

	@Override
	public void onStartRun() {
		pushButton.setEnabled(false);
		popButton.setEnabled(false);
	}

	@Override
	public void onEndRun() {
		pushButton.setEnabled(true);
		popButton.setEnabled(true);
	}

	@Override
	public void onError(String msg) {
		pushButton.setEnabled(true);
		popButton.setEnabled(true);
	}

	@Override
	public void onHalt() {}

	@Override
	public void onReset(ProgramMV program) {}

	@Override
	public void onPush(Integer value) {
		this.model.addElement(value);
	}

	@Override
	public void onPop(Integer value) {
		this.model.remove(this.model.getSize()-1);
	}

	@Override
	public void onStackReset() {
		model.clear();
	}
}
