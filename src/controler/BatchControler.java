package controler;

import exceptions.ExceptionDivisionByZero;
import exceptions.ExceptionMissingOperand;
import exceptions.ExceptionWrongDirection;
import model.CPU;

public class BatchControler extends Controler {
	
	public BatchControler(CPU cpu) {
		super(cpu);
	}
	
	public void start() { 
		try {
			this.run();
		} catch (ExceptionMissingOperand e) {
		} catch (ExceptionDivisionByZero e) {	
		} catch (ExceptionWrongDirection e) {
		} 
	}
}
