package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	public AccountDAO() throws ClassNotFoundException {
		Class.forName(DbInfo.DRIVER);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USERNAME, DbInfo.PASSWORD);
	}

	public void createAccount(AccountVO vo) throws SQLException, CreateAccountException {
		if (vo.getBalance() < 1000) {
			throw new CreateAccountException("계좌 개설시 초기 납입근은 1000원 이상이어야 합니다");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "INSERT INTO account (account_no, name,password,balance) VALUES(account_seq.NEXTVAL, ?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setInt(3, vo.getBalance());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	// 강사님이랑 내용다름
	/*
	 * public int findBalanceByAccountNo(String string, String string2) throws
	 * SQLException, AccountNotFoundException, NotMatchedPasswordException {
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * int balance=0; try { con = getConnection(); String findSql =
	 * "SELECT * FROM account WHERE account_no=?"; pstmt =
	 * con.prepareStatement(findSql); pstmt.setString(1, string); rs =
	 * pstmt.executeQuery(); if(!rs.next()) throw new
	 * AccountNotFoundException("존재하지 않는 계좌번호 입력"); pstmt.close(); rs.close();
	 * 
	 * String passwordSql =
	 * "SELECT  balance FROM account WHERE account_no=? AND password =?"; pstmt =
	 * con.prepareStatement(passwordSql); pstmt.setString(1, string);
	 * pstmt.setString(2, string2); rs = pstmt.executeQuery(); if(rs.next()) balance
	 * = rs.getInt(1); else throw new NotMatchedPasswordException("잘못된 비번입력");
	 * }finally { closeAll(rs, pstmt, con); } return balance; }
	 */

	public int findBalanceByAccountNo(String accountNo, String password)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int balance = 0;
		try {
			con = getConnection();
			String sql = "SELECT password, balance FROM account where account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 계좌번호에 해당하는 계좌가 있으면
				if (rs.getString(1).equals(password)) // 매개변수 password와 db의 password가 일치하면
					balance = rs.getInt(2);
				else // 매개변수 password와 db의 password가 일치하지 않으면
					throw new NotMatchedPasswordException("계좌의 비밀번호가 일치하지 않습니다");
			} else { // 계좌번호에 해당하는 계좌가 존재하지 않으면
				throw new AccountNotFoundException("계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}
			pstmt.close();
			rs.close();
		} finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}

	// 계좌번호 존재 유무와 비밀번호 일치 여부를 확인하는 메서드
	public void checkAccountNoAndPassword(String accountNo, String password)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT password FROM account WHERE account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (!(rs.getString(1).equals(password)))
					throw new NotMatchedPasswordException("비밀번호가 일치하지 않습니다");
			} else {
				throw new AccountNotFoundException("존재하는 계좌번호가 아닙니다.");
			}

		} finally {
			closeAll(rs, pstmt, con);
		}

	}

	/**
	 * 계좌에 입금하는 메서드 입금액이 0원 이하이면 noMoneyException을 발생시키고 전파 계좌번호가 존재하지 않으면
	 * AccountNotFoundException을 발생시키고 전파 패스원드가 일치하지 않으면
	 * NotMatchedPasswordException을 발생시키고 전파 입금액이 0원을 초과하고 계좌번호 존재하고 패스워드도 일치하면 입금처리
	 * 
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws NoMoneyException
	 * @throws NotMatchedPasswordException
	 * @throws AccountNotFoundException
	 * @throws SQLException
	 */
	// 강사님이 내용수정하셨으니 inst 확인해볼것!!
	public void deposit(String accountNo, String password, int money)
			throws NoMoneyException, SQLException, AccountNotFoundException, NotMatchedPasswordException {
		if (money <= 0)
			throw new NoMoneyException("입금액을 0원이상 설정해주세요");

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			int balance = findBalanceByAccountNo(accountNo, password);
			con = getConnection();
			String sql = "UPDATE account SET balance =? WHERE account_no=? AND password=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, balance + money);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public void withdraw(String accountNo, String password, int money) throws SQLException, AccountNotFoundException,
			NotMatchedPasswordException, NoMoneyException, InsufficientBalanceException {
		if (money <= 0)
			throw new NoMoneyException("출금액을 0원이상 설정해주세요");

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			int balance = findBalanceByAccountNo(accountNo, password);
			if (balance < money) {
				throw new InsufficientBalanceException("잔액이 부족합니다");
			}
			con = getConnection();
			String sql = "UPDATE account SET balance=? WHERE account_no=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, balance - money);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	/*
	 * public void transfer(String accountNo, String password, int money, String
	 * receiverAccount) throws SQLException, AccountNotFoundException,
	 * NotMatchedPasswordException, InsufficientBalanceException, NoMoneyException {
	 * Connection con = null; PreparedStatement pstmt = null; if(money<=0) throw new
	 * NoMoneyException("송금액은 0원 이상이어야 합니다"); int balance =
	 * findBalanceByAccountNo(accountNo, password); if(balance<money) throw new
	 * InsufficientBalanceException("잔액이 부족합니다"); try { con = getConnection();
	 * con.setAutoCommit(false); String
	 * senderSql="UPDATE account SET balance=balance+? WHERE account_no=?"; String
	 * receiverSql = "UPDATE account SET balance=balance-? WHERE account_no=?";
	 * pstmt = con.prepareStatement(senderSql); pstmt.setInt(1, money);
	 * pstmt.setString(2, accountNo); pstmt.executeUpdate(); pstmt.close();
	 * 
	 * pstmt=con.prepareStatement(receiverSql); pstmt.setInt(1, money);
	 * pstmt.setString(2, receiverSql);
	 * 
	 * pstmt.executeUpdate(); con.commit(); }catch(Exception e) { con.rollback();
	 * throw e; }finally { closeAll(pstmt, con); } }
	 */
	public boolean existAccountNo(String accountNo) throws SQLException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT COUNT(*) FROM account WHERE account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = true;
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

	public void transfer(String accountNo, String password, int money, String receiverAccount) throws SQLException,
			AccountNotFoundException, NotMatchedPasswordException, InsufficientBalanceException, NoMoneyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		if (money <= 0)
			throw new NoMoneyException("송금액은 0원 이상이어야 합니다");

		if (existAccountNo(receiverAccount) == false)
			throw new AccountNotFoundException("수신인 계좌가 존재하지 않습니다");
		try {
			con = getConnection();
			con.setAutoCommit(false);

			String receiverSql = "UPDATE account SET balance=balance+? WHERE account_no=?";
			withdraw(accountNo, password, money);

			pstmt = con.prepareStatement(receiverSql);
			pstmt.setInt(1, money);
			pstmt.setString(2, receiverAccount);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally {
			closeAll(pstmt, con);
		}
	}
	/*
	 * public void transfer(String accountNo, String password, int money, String
	 * receiverAccount) throws SQLException, AccountNotFoundException,
	 * NotMatchedPasswordException, InsufficientBalanceException, NoMoneyException {
	 * Connection con = null; PreparedStatement pstmt = null; if(money<=0) throw new
	 * NoMoneyException("송금액은 0원 이상이어야 합니다"); int balance =
	 * findBalanceByAccountNo(accountNo, password); if(balance<money) throw new
	 * InsufficientBalanceException("잔액이 부족합니다");
	 * if(existAccountNo(receiverAccount)==false) throw new
	 * AccountNotFoundException("수신인 계좌가 존재하지 않습니다"); try { con = getConnection();
	 * con.setAutoCommit(false); String
	 * senderSql="UPDATE account SET balance=balance-? WHERE account_no=?"; String
	 * receiverSql = "UPDATE account SET balance=balance+? WHERE account_no=?";
	 * pstmt = con.prepareStatement(senderSql); pstmt.setInt(1, money);
	 * pstmt.setString(2, accountNo); pstmt.executeUpdate(); pstmt.close();
	 * pstmt=con.prepareStatement(receiverSql); pstmt.setInt(1, money);
	 * pstmt.setString(2, receiverAccount); pstmt.executeUpdate(); con.commit();
	 * }catch(Exception e) { con.rollback(); throw e; }finally { closeAll(pstmt,
	 * con); } }
	 */
}
