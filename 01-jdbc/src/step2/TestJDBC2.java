package step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * member TABLE 에 INSERT 를 하는 예제
 * 
 *  JDBC 개발단계
 *  1. JDBC Driver loading
 *  2. Connection
 *  3. PreparedStatement -> INSERT sql 실행  executeUpdate() (SELECT 시에는 executeQuery())
 *  4. close
 */
public class TestJDBC2 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("oracle jdbc driver loading");
			Connection con =  DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott", "tiger");
			System.out.println("connection");
			String sql = "INSERT INTO member(id,password, name,address)  VALUES (?,?,?,?)";
		
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "jsp"); //1의 의미는 첫번째 ? -> id 에 jsp를 할당
			pstmt.setString(2, "a"); //2의 의미는 두번째 ? -> password에 a를 할당
			pstmt.setString(3, "이효리"); //3의 의미는 세번째 ? -> name 에  이효리를 할당
			pstmt.setString(4, "애월읍"); //4의 의미는 네번째 ? -> address에  애월읍을 할당
			//sql 실행 - 두번 넣어줄경우에는 execption 발생 -unique constraint (SCOTT.SYS_C007002) violated
			int result = pstmt.executeUpdate(); //sql 실행해서 테이블에 영향을 준 row수가 반환
			System.out.println("insert member : " + result);
			
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 
	}
}
