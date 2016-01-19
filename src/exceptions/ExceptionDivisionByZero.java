package exceptions;

@SuppressWarnings("serial")
public class ExceptionDivisionByZero extends Error {
	public ExceptionDivisionByZero(String message){
		super(message);
	}
}
