package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {
	public EmployeeDAO() throws ClassNotFoundException {
		Class.forName(DbInfo.DRIVER);
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con !=null) {
			con.close();
		}
	}
	public void closeAll(ResultSet rs,  PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(pstmt, con);	
	}
	public void register(Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(DbInfo.URL, DbInfo.USERNAME, DbInfo.PASSWORD);
			String sql = "INSERT INTO s_employee(empno, name, job, salary) VALUES(s_employee_seq.NEXTVAL, ?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getSalary());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
		
	}
	public ArrayList<Employee> getEmployeeListByHighSalAndJob(String job) throws SQLException {
		ArrayList<Employee> list = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(DbInfo.URL, DbInfo.USERNAME, DbInfo.PASSWORD);
			
			//String sql = "SELECT  empno,name,job,salary FROM s_employee WHERE salary = (SELECT MAX(salary) FROM s_employee) AND job=?";
			//String sql ="SELECT empno,name, job, salary FROM s_employee WHERE job=? AND salary =(SELECT MAX(salary) FROM s_employee WHERE job=?)";
			StringBuilder sb = new StringBuilder("SELECT empno,name, job, salary ");
			sb.append("FROM s_employee ");
			sb.append("WHERE job=? ");
			sb.append("AND salary =(SELECT MAX(salary) FROM s_employee WHERE job=?)");
			//pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, job);
			pstmt.setString(2, job);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
}
