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
			//String sql = "INSERT INTO guestbook(questbook_no,title,content) VALUES (guestbook_seq.nextval,?,?)";
			//pstmt = con.prepareStatement(sql);
			StringBuilder sql = new StringBuilder();
			sql.append("NSERT INTO guestbook(questbook_no,title,content) ");
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
}
