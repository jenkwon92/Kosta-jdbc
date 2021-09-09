package model;

public class NotMatchedPasswordException extends Exception{
	private static final long serialVersionUID = -638172545387708394L;
	public NotMatchedPasswordException(String message) {
		super(message);
	}
}
