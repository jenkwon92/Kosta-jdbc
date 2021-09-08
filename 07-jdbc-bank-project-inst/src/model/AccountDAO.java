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
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USERNAME, DbInfo.PASSWORD);
	}
	public void createAccount(AccountVO accountVO) throws CreateAccountException, SQLException {
		if(accountVO.getBalance()<1000)
			throw new CreateAccountException("계좌 개설시 초기 납입금은 1000원 이상이어야 합니다");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			StringBuilder sql=new StringBuilder("insert into account(account_no,name,password,balance) ");
			sql.append("values(account_seq.nextval,?,?,?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, accountVO.getName());
			pstmt.setString(2, accountVO.getPassword());
			pstmt.setInt(3, accountVO.getBalance());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public int findBalanceByAccountNo(String accountNo, String password) throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int balance=0;
		try {
			con = getConnection();
			String sql = "SELECT password, balance FROM account where account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			if(rs.next()) { //계좌번호에 해당하는 계좌가 있으면
				if(rs.getString(1).equals(password)) //매개변수 password와 db의 password가 일치하면
					balance = rs.getInt(2);
				else //매개변수 password와 db의 password가 일치하지 않으면
					throw new NotMatchedPasswordException("계좌의 비밀번호가 일치하지 않습니다");
			}else { //계좌번호에 해당하는 계좌가 존재하지 않으면
				throw new AccountNotFoundException("계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}
			pstmt.close();
			rs.close();
		}finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}
}

















