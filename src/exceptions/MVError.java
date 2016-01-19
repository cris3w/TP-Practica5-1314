package exceptions;

@SuppressWarnings("serial")
public class MVError extends Error {
	public MVError(String message){
		super(message);
	}
}
