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

	public void createAccount(AccountVO vo) throws SQLException, CreateAccountException  {
		if(vo.getBalance()<1000) {
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

	public Integer findBalanceByAccountNo(String string, String string2) throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int balance=0;
		try {
			con = getConnection();
			String findSql = "SELECT * FROM account WHERE account_no=?";
			pstmt = con.prepareStatement(findSql);
			pstmt.setString(1, string);
			rs = pstmt.executeQuery();
			if(!rs.next())
				throw new AccountNotFoundException("존재하지 않는 계좌번호 입력");
			pstmt.close();
			rs.close();
			
			String passwordSql = "SELECT  balance FROM account WHERE account_no=? AND password =?";
			pstmt = con.prepareStatement(passwordSql);
			pstmt.setString(1, string);
			pstmt.setString(2, string2);
			rs = pstmt.executeQuery();
			if(rs.next())
				balance = rs.getInt(1);
			else
				throw new NotMatchedPasswordException("잘못된 비번입력");
		}finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}
}
