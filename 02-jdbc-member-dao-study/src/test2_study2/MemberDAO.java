package test2_study2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * DAO : Data Access Object 데이터 액세스 로직을 정의하는 객체
 */
public class MemberDAO {
	private String driver = "oracle.jdbc.OracleDriver"; 
	private String url ="jdbc:oracle:thin:@127.0.0.1:1521:xe"; //접속문자열
	private String username ="scott";
	private String pass = "tiger";
	public MemberDAO() throws ClassNotFoundException { //driver로딩이 안될경우 더이상 진행하지 않겠다
		//Class.forName("oracle.jdbc.OracleDriver");  //로드하고 싶은 클래스경로를 String으로 명시
		Class.forName(driver); 
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException  {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	//오버로딩
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt,con);
	}
	/*
	 * DAO 의 메서드에서는 Exception을 main으로 throws 처리한다 
	 * 
	 * Connection 
	 * insert sql 정의
	 * PreparedStatement
	 * sql 실행
	 * close
	 */
	public void registerMember(MemberVO vo) throws SQLException {
		Connection con =null;
		PreparedStatement pstmt=null;
		try {
			con = DriverManager.getConnection(url,username,pass);
			String sql = "INSERT INTO member (id,password,name,address) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			pstmt.executeUpdate(); 
		}finally {
			if(pstmt !=null)
				pstmt.close();
			if(con !=null)
				con.close();
		}
		
	}
}






