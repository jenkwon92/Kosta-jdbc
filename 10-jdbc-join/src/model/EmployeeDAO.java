package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public EmployeeVO findEmployeeByEmpno(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO employeeVO = null;

		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder("SELECT e.deptno,e.ename, e.sal, d.deptno, d.dname, d.loc,d.tel ");
			sql.append("FROM k_employee e, department d ");
			sql.append("WHERE e.deptno = d.deptno ");
			sql.append("AND e.empno=?");
			// 아래와 같이도 가능
			// SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc, d.tel FROM
			// k_employee e INNER JOIN department d ON e.deptno=d.deptno WHERE e.empno=1
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				employeeVO = new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getInt(3),
						new DepartmentVO(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)));
				/*
				 * DepartmentVO dvo =new DepartmentVO(rs.getInt(4),
				 * rs.getString(5),rs.getString(6),rs.getString(7)); employeeVO = new
				 * EmployeeVO(rs.getInt(1),rs.getString(2),rs.getInt(3), dvo);
				 */

				/*
				 * employeeVO =new EmployeeVO(); employeeVO.setEmpNo(empno);
				 * employeeVO.setEname(rs.getString(1)); employeeVO.setSal(rs.getInt(2));
				 * DepartmentVO departmentVO = new DepartmentVO();
				 * departmentVO.setDeptno(rs.getInt(3)); departmentVO.setDname(rs.getString(4));
				 * departmentVO.setLoc(rs.getString(5)); departmentVO.setTel(rs.getString(6));
				 * employeeVO.setDepartmentVO(departmentVO);
				 */

			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return employeeVO;
	}
}
