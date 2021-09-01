package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * java application 과 database 연동
 * 1. jdbc driver loading (jdk 1.6 버전 이상에서는 생략가능)
 * 2. db connection
 * 3. PreparedStatement
 * 4. ResultSet
 * 5. close
 */
public class TestJDBC1 {
	public static void main(String[] args) {
		//JRE System -> ojdbc에 존재함
		String driver ="oracle.jdbc.OracleDriver";  //oracle.jdbc에 OracleDriver.class명시
		try {
			//oracle jdbc 드라이버 로딩
			Class.forName(driver); //Class.forName() -> class를 loading시킨다 -> static 정보가 meta space에 저장
			System.out.println("jdbc.driver.loading");
			//oracle database와 연결
			//String url= "jdbc:oracle:thin:@221.150.136.4:1521:xe";
			String url= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			Connection con = DriverManager.getConnection(url,"scott","tiger");
			System.out.println("oracle db와 connection : "+con); //예외가 나면 연결이 안된것
			String sql="SELECT id,password, name, address FROM member";
			PreparedStatement pstmt = con.prepareStatement(sql); 
			ResultSet rs = pstmt.executeQuery(); //sql실행
			while(rs.next()) { //조회 결과 행이 존재하면 true
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) { //ext안에 ojdbc안넣어 준경우에 exception발생
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
