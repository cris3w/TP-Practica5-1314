package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controler.Controler;
import io.InStrategy;

@SuppressWarnings("serial")
public class InputPanel extends JPanel {
	
	private Controler ctrl;
	private JTextArea inputTextArea;
	private InStrategy old;
	
	public class InStrategyGUI implements InStrategy {
		StringBuilder contentBuilder;
		int pos;
		
		public InStrategyGUI() throws IOException {
			this.pos = 0;
			
			int n;
			char c;
			StringBuilder str = new StringBuilder();
			n = old.read();
			while (n != -1) {
				c = (char) n;
				str.append(c);
				n = old.read();
			}
			this.contentBuilder = str;
			inputTextArea.setText(this.contentBuilder.toString());
		}
		
		public void open() {}
		
		public void close() throws IOException {
			old.close();
		} 
		
		public int read() throws IOException {
			int c = -1;
			if (pos != contentBuilder.length()) {
				c = this.contentBuilder.getCharAt(this.pos);
				if (c != 10 && c != 13) {
					this.contentBuilder.setCharAt(this.pos, '*');
				}
				inputTextArea.setText(this.contentBuilder.toString());
				this.pos++;
			}
			return c;
		}
	}
	
	public InputPanel(Controler ctrl) throws IOException {
		this.ctrl = ctrl;
		this.old = ctrl.getInStrategy();
		initGUI();
	}
	
	private void initGUI() throws IOException {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Input"));
		inputTextArea = new JTextArea(3, 10);
		inputTextArea.setFont(new Font("Courier", Font.PLAIN, 14));
		inputTextArea.setEditable(false);
		this.add(new JScrollPane(inputTextArea));
		
		this.old = ctrl.getInStrategy();
		InStrategy inNew = new InStrategyGUI();
		ctrl.setInStrategy(inNew);
	}
}
