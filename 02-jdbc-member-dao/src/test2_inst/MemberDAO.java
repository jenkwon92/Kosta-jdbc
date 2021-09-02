package test2_inst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			
			pstmt.executeUpdate(); 
		}finally { //위에 줄에서 하나라도 오류가 나더라도 finally 동작해서 close를 해주기위해 try-finally구조로
			closeAll(pstmt,con); //finally 안써준다면 예외상황시 close까지 도달하지 못함  -- close안해주면 db connection 횟수는 제한되어있기때문에 어느순간 시스템이 뻗어버림
		}
	}
	public MemberVO findMemberById(String id) throws SQLException {
		MemberVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url,username,pass);
			String sql ="SELECT password, name, address FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); //첫번째 물음표
			
			rs = pstmt.executeQuery(); //결과를 받아야하기때문에 exectueQuery()
			//primary key 로 검색했으므로 회원정보가 1명 존재하거나 존재하지 않는다 
			if(rs.next()) { //검색 결과 행row가 존재하면 true를 반환
				vo = new MemberVO(id, rs.getString(1),rs.getString(2),rs.getString(3));
			}	
		}finally {
			closeAll(rs,pstmt,con);
		}
		return vo;
	}
	public ArrayList<MemberVO> getAllMemberList() throws SQLException {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url,username, pass);
			String sql = "SELECT id,name FROM member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//방법 1
				list.add(new MemberVO(rs.getString(1), null,rs.getString(2),null));
				//방법 2
				// MemberVo vo = new Member();
				//vo.setId(rs.getString(1));
				//vo.setName(rs.getString(2));
				//list.add(vo);
			}
		}finally {
			closeAll(rs,pstmt,con);
		}
		return null;
	}
}






