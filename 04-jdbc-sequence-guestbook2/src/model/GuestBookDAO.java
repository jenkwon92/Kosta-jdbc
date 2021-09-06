package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestBookDAO {
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String username = "scott";
	private String password = "tiger";

	public GuestBookDAO() throws ClassNotFoundException {
		Class.forName(driver);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
	//오버로딩
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		//위의메서드를 재사용
		closeAll(pstmt, con);
	}

	public void regist(GuestBookDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url,username,password);
			//String sql = "INSERT INTO guestbook(guestbook_no,title,content) VALUES (guestbook_seq.nextval,?,?)";
			//pstmt = con.prepareStatement(sql);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO guestbook(guestbook_no,title,content) ");
			sql.append("VALUES (guestbook_seq.nextval,?,?)");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.executeUpdate();	
		}finally {
			closeAll(pstmt, con);
		}
	}

	public ArrayList<GuestBookDTO> getAllGuestBookList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<GuestBookDTO> list = new ArrayList<GuestBookDTO>();
		try {
			con = DriverManager.getConnection(url,username,password);
			String sql = "SELECT questbook_no, title,content FROM guestbook ORDER BY questbook_no DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new GuestBookDTO(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	/*
	 * 글등록을 하고 나면 DB에서 발급한 글번호가 guestBookNO에 할당시키도록
	 *  기존의 register메서드를 업데이트한 메서드
	 *  Connection
	 *  PreparedStatement
	 *  insert sql 실행 (sequence명.nextval)
	 *  pstmt.close()
	 *  PreparedStatement
	 *  select sql 실행 (sequence명.currval)
	 *  ResultSet
	 *  closeAll()
	 */
	public void registVer2(GuestBookDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = DriverManager.getConnection(url,username,password);
			String insertSql = "INSERT INTO guestbook (guestbook_no, title, content) VALUES(guestbook_seq.NEXTVAL, ?,?)";
			pstmt = con.prepareStatement(insertSql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.executeUpdate();
			pstmt.close();
			String selectSql ="SELECT guestbook_seq.CURRVAL FROM DUAL";
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int guestBookNo = rs.getInt(1); //INSERT 시점에 발급받은 시퀀스(guestbook_no에 저장된 값)의 현재값을 받아온다
				dto.setGuestbookNo(guestBookNo); //dto에 할당한다
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		
	}
}
