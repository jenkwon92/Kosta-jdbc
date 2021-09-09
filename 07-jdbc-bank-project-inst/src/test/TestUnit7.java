package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.InsufficientBalanceException;
import model.NoMoneyException;
import model.NotMatchedPasswordException;

//step7. 계좌이체 테스트
public class TestUnit7 {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			String senderAccountNo = "1";
			String password = "1234";
			int money = 100;
			String receiverAccountNo = "2";
			System.out.println("이체 전 송금자 계좌잔액 : "+dao.findBalanceByAccountNo(senderAccountNo, password));
			System.out.println("이체 전 수금자 계좌잔액 : "+dao.findBalanceByAccountNo(receiverAccountNo, "1111"));
			dao.transfer(senderAccountNo, password, money, receiverAccountNo);
			System.out.println("이체 후 송금자 계좌잔액 : "+dao.findBalanceByAccountNo(senderAccountNo, password));
			System.out.println("이체 후 수금자 계좌잔액 : "+dao.findBalanceByAccountNo(receiverAccountNo, "1111"));


		} catch (NoMoneyException e) { // 이체액은 0원을 초과해야 합니다
			e.printStackTrace();
		} catch (AccountNotFoundException e) { //계좌번호에 해당하는 계좌가 존재하지 않습니다 or 이체받을 계좌가 존재하지 않습니다.
			e.printStackTrace();
		} catch (NotMatchedPasswordException e) {//계좌의 패스워드가 일치하지 않습니다
			e.printStackTrace();
		} catch (InsufficientBalanceException e) { //잔액부족으로 이체할 수 없습니다.
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
