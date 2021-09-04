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
		if(pstmt !=null) 
			pstmt.close();
		if(con != null)
			con.close();
	}
	
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs !=  null)
			rs.close();
		closeAll(pstmt, con);
	}
	
	public void regist(GuestBookDTO dto) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO guestbook(guestbook_no, title, content) VALUES(guestbook_seq.NEXTVAL,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.executeUpdate();	
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	public ArrayList<GuestBookDTO> getAllGuestBookList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
		}finally {
			
		}
	}
}
