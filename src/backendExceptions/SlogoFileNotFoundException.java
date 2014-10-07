package backendExceptions;

public class SlogoFileNotFoundException extends BackendException {

	private final static String FILE_NOT_FOUND = "File not found: ";
	
	public SlogoFileNotFoundException(Exception ex, String fileName) {
		super(ex, createMessageString(fileName));
	}
	
	private static String createMessageString(String fileName){
		return FILE_NOT_FOUND + fileName;
	}
}
