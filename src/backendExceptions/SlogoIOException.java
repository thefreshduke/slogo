package backendExceptions;

public class SlogoIOException extends BackendException {
	//TODO: Change this message to something more descriptive
	private static final String IO_EXCEPTION = "Input Ouput Exeception";

	public SlogoIOException(Exception ex, String cause) {
		super(ex, IO_EXCEPTION);
	}
	
	public SlogoIOException(Exception ex) {
		this(ex, "");
	}

}
