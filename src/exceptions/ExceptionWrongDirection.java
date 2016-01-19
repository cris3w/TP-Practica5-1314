package exceptions;

@SuppressWarnings("serial")
public class ExceptionWrongDirection extends Error {
	public ExceptionWrongDirection(String message){
		super(message);
	}
}
