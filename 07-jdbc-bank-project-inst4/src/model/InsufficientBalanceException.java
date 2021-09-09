package model;

public class InsufficientBalanceException extends Exception{
	private static final long serialVersionUID = 5794651172889172813L;
	public InsufficientBalanceException(String message) {
		super(message);
	}
}
