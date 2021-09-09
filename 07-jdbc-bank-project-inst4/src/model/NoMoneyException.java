package model;

public class NoMoneyException extends Exception {
	private static final long serialVersionUID = 6681371889159355306L;
	public NoMoneyException(String message) {
		super(message);
	}
}
