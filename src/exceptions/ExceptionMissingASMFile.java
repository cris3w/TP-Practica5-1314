package exceptions;

@SuppressWarnings("serial")
public class ExceptionMissingASMFile extends Error {
	public ExceptionMissingASMFile(String message){
		super(message);
	}
}
