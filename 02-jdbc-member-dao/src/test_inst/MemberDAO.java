package test_inst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * DAO : Data Access Object 데이터 액세스 로직을 정의하는 객체
 */
public class MemberDAO {
	private String driver = "oracle.jdbc.OracleDriver";
	private String url ="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String username ="scott";
	private String pass = "tiger";
	public MemberDAO() throws ClassNotFoundException { //driver로딩이 안될경우 더이상 진행하지 않겠다
		Class.forName(driver);
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	/*
	 * DAO 의 메서드에서는 Exception을 mainㅇ로 throws 처리한다 
	 * 
	 * Connection 
	 * insert sql 정의
	 * PreparedStatement
	 * sql 실행
	 * close
	 */
	public void registerMember(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url,username,pass);
			String sql ="INSERT INTO member (id,password,name,address) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			
			int result = pstmt.executeUpdate(); //sql 실행해서 테이블에 영향을 준 row수가 반환
			System.out.println("insert member : " + result);
			
		}finally {
			closeAll(pstmt,con);
		}
	}
}






