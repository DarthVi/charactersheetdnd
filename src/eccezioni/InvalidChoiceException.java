package eccezioni;

public class InvalidChoiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7471998374211592200L;
	
	public InvalidChoiceException() {
		super();
	}
	
	public InvalidChoiceException(String message) {
		super(message);
	}

}
