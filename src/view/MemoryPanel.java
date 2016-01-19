package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import controler.Controler;
import model.CPUObserver;
import model.Memory;
import model.MemoryObserver;
import model.Observable;
import model.ProgramMV;
import model.Stack;
import model.instructions.Instruction;


@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements MemoryObserver<Integer>, CPUObserver, ActionListener {
	
	private Controler ctrl;
	private TableModel model;
	private JTextField memPos, memValue;
	private JLabel posLabel, valueLabel;
	private JButton setButton; 	
	
	private class TableModel extends AbstractTableModel {
		
		TreeMap<Integer, Integer> content;
		String[] columnsNames = {"Adress","Value"};
		
		public TableModel() {
			content = new TreeMap<Integer, Integer>();
		}

		public void setValue(int index, int value) {
			content.remove(index);
			content.put(index, value);
			this.fireTableStructureChanged();
		}

		public void reset() {
			content.clear();
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Set<Integer> x = this.content.keySet();
			Integer key= (Integer)x.toArray()[rowIndex];
			if (columnIndex == 0) {
				return key;
			} else {
				return this.content.get(key);
			}
		}
		
		@Override
		public int getColumnCount() {
			return 2;
		}
		@Override
		public int getRowCount() {
			return this.content.size();
		}
		
		public String getColumnName(int column){
			return this.columnsNames[column];
		}
	}
	
	MemoryPanel(Controler ctrl, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		initGUI();
		cpu.addObserver(this);
		memory.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Memory"));
		
		model = new TableModel();
		JTable table = new JTable(model);
		this.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Courier", Font.PLAIN, 14));
		
		JPanel buttonsPanel = new JPanel();
		this.add(buttonsPanel, BorderLayout.PAGE_END);
		
		posLabel = new JLabel("Position");
		buttonsPanel.add(posLabel);
		memPos = new JTextField(5);
		buttonsPanel.add(memPos);
		valueLabel = new JLabel("Value");
		buttonsPanel.add(valueLabel);
		memValue = new JTextField(5);
		buttonsPanel.add(memValue);	
		
		setButton = new JButton("Set");
		setButton.addActionListener(this);
		buttonsPanel.add(setButton);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setButton) {
			String nText = memValue.getText();
			String posText = memPos.getText();
			try {
				int n = Integer.parseInt(nText);
				int pos = Integer.parseInt(posText);
				ctrl.setValueMemory(pos, n);
			} catch(NumberFormatException e1) {
				JFrame emergente = new JFrame();
				JOptionPane.showMessageDialog(emergente, "El valor tiene que ser numerico", "Formato erroneo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Stack<Integer> stack, Memory<Integer> memory) {}

	@Override
	public void onStartRun() {
		setButton.setEnabled(false);
	}

	@Override
	public void onEndRun() {
		setButton.setEnabled(true);
	}

	@Override
	public void onError(String msg) {
		setButton.setEnabled(true);
	}

	@Override
	public void onHalt() {}

	@Override
	public void onReset(ProgramMV program) {}

	@Override
	public void onWrite(int index, Integer value) {
		model.setValue(index, value);
	}

	@Override
	public void onMemReset() {
		model.reset();
	}
}
