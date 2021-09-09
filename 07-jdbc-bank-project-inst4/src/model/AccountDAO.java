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
	/**
	 * 계좌의 잔액을 조회하는 메서드 
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException을 발생시키고 전파한다
	 * 패스워드가 일치하지 않으면 NotMatchedPasswordException을 발생시키고 전파한다 
	 * 계좌번호에 해당하는 계좌가 존재하고 비밀번호가 일치하면 잔액을 반환한다 
	 * @param accountNo
	 * @param password
	 * @return balance
	 * @throws SQLException 
	 * @throws AccountNotFoundException 
	 * @throws NotMatchedPasswordException 
	 */
	public int findBalanceByAccountNo(String accountNo, String password) throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		int balance=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="select password,balance from account where account_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {//계좌번호에 해당하는 계좌가 있으면 
				if(rs.getString(1).equals(password)) {//매개변수 password와 db의 password가 일치하면 
					balance=rs.getInt(2);
				}else {//매개변수 password와 db의 password가 일치하지 않으면 
					throw new NotMatchedPasswordException("계좌의 패스워드가 일치하지 않습니다");
				}
			}else {//계좌번호에 해당하는 계좌가 존재하지 않으면 
				throw new AccountNotFoundException(accountNo+"계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}
	/**
	 * 계좌번호 유무와 계좌번호에 따른 비밀번호 일치여부를 확인하는 메서드 
	 * 매개변수에 전달된 계좌번호가 존재하지 않으면 AccountNotFoundException 발생시키고 전파
	 * 매개변수에 전달된 계좌번호에 대한 비밀번호가 일치하지 않으면 NotMatchedPasswordException 발생시키고 전파 
	 * @param accountNo
	 * @param password
	 * @throws SQLException 
	 * @throws AccountNotFoundException 
	 * @throws NotMatchedPasswordException 
	 */
	public void checkAccountNoAndPassword(String accountNo, String password) throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="select password from account where account_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(password)==false)//비밀번호가 일치하지 않으면 
				throw new NotMatchedPasswordException("계좌 비밀번호가 일치하지 않습니다");	
			}else {// 조회결과가 없으면 ( 계좌번호에 해당하는 계좌가 없으면 ) 
				throw new AccountNotFoundException(accountNo+" 계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}			
		}finally {
			closeAll(rs, pstmt, con);
		}
	}
	
	
	
	/**
	 * 계좌에 입금하는 메서드 
	 * 입금액이 0원 이하이면 NoMoneyException을 발생시키고 전파 
	 * 계좌번호가 존재하지 않으면 AccountNotFoundException을 발생시키고 전파 
	 * 패스워드가 일치하지 않으면 NotMatchedPasswordException을 발생시고 전파
	 * 입금액이 0원을 초과하고 계좌번호 존재하고 패스워드 일치하면 입금처리 
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws NoMoneyException 
	 * @throws NotMatchedPasswordException 
	 * @throws AccountNotFoundException 
	 * @throws SQLException 
	 */
	public void deposit(String accountNo, String password, int money) throws NoMoneyException, 
	SQLException,AccountNotFoundException, NotMatchedPasswordException {
		if(money<=0)
			throw new NoMoneyException("입금액은 0원을 초과해야 합니다");
		//아래 메서드로 계좌번호 존재유무와 비밀번호 일치여부를 체크한다 
		checkAccountNoAndPassword(accountNo, password);
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="update account set balance=balance+? where account_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public void withdraw(String accountNo, String password, int money) {
	}
	
}
























