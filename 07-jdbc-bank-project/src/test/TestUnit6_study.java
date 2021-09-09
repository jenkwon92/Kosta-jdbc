package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.InsufficientBalanceException;
import model.NoMoneyException;
import model.NotMatchedPasswordException;

public class TestUnit6_study {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			String accountNo = "1"; // 존재하지 않는 계좌번호 테스트
			String password = "1234"; // 패스워드테스트
			int money = 10; // 출금액 테스트
			String receiverAccount ="22";
			System.out.println("출금전 계좌잔액 :" +dao.findBalanceByAccountNo(accountNo, password));
			dao.transfer(accountNo, password, money, receiverAccount);
			System.out.println("출금완료");
			System.out.println("출금후 계좌잔액 :" +dao.findBalanceByAccountNo(accountNo, password));
			// 비즈니스 적인 예외시에 getMessage()를 남긴다.
		} catch (NoMoneyException e) {
			System.out.println(e.getMessage());
		}catch (InsufficientBalanceException e) {
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
