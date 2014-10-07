package backendExceptions;

public class BackendException extends Exception{

	public BackendException(Exception ex, String messageToShow){
		super(messageToShow, ex);
	}
}
