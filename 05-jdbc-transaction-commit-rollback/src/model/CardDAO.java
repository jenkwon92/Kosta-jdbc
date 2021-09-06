package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO {
	public CardDAO() throws ClassNotFoundException {
		Class.forName(DbInfo.DRIVER);
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
	/*
	 * 트랜잭션 처리작업을 하지 않아서 문제 발생되는 메서드의 예
	 */
	public void registerCardAndPoint(String id, String name, String pointType, int point) throws SQLException {
		Connection con  = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(DbInfo.URL , DbInfo.USERNAME, DbInfo.PASSWORD);
			String insertCardSql ="INSERT INTO card(id, name) VALUES(?,?)";
			String insertPointSql = "INSERT INTO point(id, point_type,point) VALUES (?,?,?)";
			pstmt = con.prepareStatement(insertCardSql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();
			System.out.println("카드 등록 ok "+result);
			pstmt.close();
			pstmt = con.prepareStatement(insertPointSql);
			pstmt.setString(1, id);
			pstmt.setString(2, pointType);
			pstmt.setInt(3, point);
			int result2 = pstmt.executeUpdate();
			System.out.println("포인트 등록 ok"+result2);
		}finally {
			closeAll(pstmt, con);
		}
	}
}
