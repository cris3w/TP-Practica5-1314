package exceptions;

@SuppressWarnings("serial")
public class ExceptionMissingOperand extends Error {
	public ExceptionMissingOperand(String message){
		super(message);
	}
}
