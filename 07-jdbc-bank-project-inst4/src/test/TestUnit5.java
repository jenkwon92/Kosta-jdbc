package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.InsufficientBalanceException;
import model.NoMoneyException;
import model.NotMatchedPasswordException;

//step5 출금 테스트 
public class TestUnit5 {
	public static void main(String[] args) {		
		try {
			AccountDAO dao=new AccountDAO();
			String accountNo="1";//출금계좌번호
			String password="1234";//계좌패스워드
			int money=100;//출금액
			System.out.println("출금전 계좌잔액:"+dao.findBalanceByAccountNo(accountNo, password));
			dao.withdraw(accountNo,password,money);
			System.out.println("출금후 계좌잔액:"+dao.findBalanceByAccountNo(accountNo, password));
		}catch(NoMoneyException e) {
			System.out.println(e.getMessage());
		}catch(AccountNotFoundException e) { 
			System.out.println(e.getMessage());
		}catch(NotMatchedPasswordException e) {	
			System.out.println(e.getMessage());
		}catch(InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
