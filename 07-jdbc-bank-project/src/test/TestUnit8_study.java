package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.InsufficientBalanceException;
import model.NoMoneyException;
import model.NotMatchedPasswordException;

//step7. 계좌이체 테스트
public class TestUnit8_study {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			String senderAccountNo="1";
			String password="1234";
			int money=100;
			String receiverAccountNo="4";
			dao.transfer(senderAccountNo, password, money, receiverAccountNo);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		} catch (NotMatchedPasswordException e) {
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			e.printStackTrace();
		} catch (NoMoneyException e) {
			e.printStackTrace();
		}
	}
}
