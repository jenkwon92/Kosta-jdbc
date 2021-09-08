package model;

public class AccountNotFoundException extends Exception{
	private static final long serialVersionUID = -3681249781517219295L;

	public AccountNotFoundException(String message) {
		super(message);
	}
	
}
