package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.NoMoneyException;
import model.NotMatchedPasswordException;

public class TestUnit3 {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			String accountNo = "1"; // 존재하지 않는 계좌번호 테스트
			String password = "1234"; // 패스워드테스트
			int money = 100; // 입금액 테스트
			System.out.println("입금전 잔액"+dao.findBalanceByAccountNo(accountNo, password));
			dao.deposit(accountNo, password, money);
			System.out.println("입금완료");
			System.out.println("입금후 잔액"+dao.findBalanceByAccountNo(accountNo, password));
			// 비즈니스 적인 예외시에 getMessage()를 남긴다.
		} catch (NoMoneyException e) {
			System.out.println(e.getMessage());
		} catch (AccountNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NotMatchedPasswordException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) { // 시스템적인 오류
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
