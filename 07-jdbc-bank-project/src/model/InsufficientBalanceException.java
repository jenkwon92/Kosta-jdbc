package model;

public class InsufficientBalanceException extends Exception{
	private static final long serialVersionUID = -4236638528117552246L;

	public InsufficientBalanceException(String message) {
		super(message);
	}	
}
