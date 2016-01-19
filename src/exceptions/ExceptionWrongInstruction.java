package exceptions;

@SuppressWarnings("serial")
public class ExceptionWrongInstruction extends Error {
	public ExceptionWrongInstruction(String message){
		super(message);
	}
}
