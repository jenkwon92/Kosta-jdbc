package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String username = "scott";
	private String password = "tiger";

	public EmployeeDAO() throws ClassNotFoundException {
		Class.forName(driver);
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
		return DriverManager.getConnection(url, username, password);
	}

	public ArrayList<EmployeeVO> findEmployeeList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder("SELECT empno, name,job,salary ");
			sql.append("FROM s_employee ");
			sql.append("WHERE job IN( SELECT job FROM s_employee GROUP BY job HAVING AVG(salary)<=(SELECT AVG(salary) FROM s_employee)) ");
			sql.append("ORDER BY salary DESC");
			pstmt =con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
}
