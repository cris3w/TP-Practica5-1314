package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controler.Controler;
import io.OutStrategy;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
	
	private Controler ctrl;
	private JTextArea outputTextArea;
	private OutStrategy old;
	
	public class OutStrategyGUI implements OutStrategy {
		StringBuilder contentBuilder;
		int pos;
		
		public OutStrategyGUI() {
			this.contentBuilder = new StringBuilder();
			this.pos = 0;
		}
		
		public void close() throws IOException {
			old.close();
		}
		
		public void write(int n) throws IOException {
			old.write(n);
			char c = (char) n;
			this.contentBuilder.append(c);
			outputTextArea.setText(this.contentBuilder.toString());
		}

		public void open() throws IOException {}
	}
	
	OutputPanel(Controler ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Output"));
		outputTextArea = new JTextArea(3, 10);
		outputTextArea.setFont(new Font("Courier", Font.PLAIN, 14));
		outputTextArea.setEditable(false);
		this.add(new JScrollPane(outputTextArea));
		
		this.old = ctrl.getOutStrategy();
		OutStrategy outNew = new OutStrategyGUI();
		ctrl.setOutStrategy(outNew);
	}
}
