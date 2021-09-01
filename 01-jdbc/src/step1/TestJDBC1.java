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
			System.out.println("oracle db와 connection : "+con); //예외가 나면 연결이 안된것  // racle.jdbc.driver.T4CConnection@27f8302d -오라클 벤더가 만들어둔 것
			
			//DBMS 각자의 방식으로 동작-  데이터 베이스 종속적 x ->결합도 낮고 유지보수성이 좋아짐
			//데이터베이스에 특화된 개발들 공부하는 것이아니라
			//jdbc 인터페이스만 공부해서 개발하기 때문에 생산성이 높아짐
			//jdbc 인터페이스로 설계해두면 DB가 변경되어도 프로그램은 변화되지 않는다 
			//캡슐화 장점:  사용자는 인터페이스만 보고 개발 -생산성이 좋아짐, 구현체가 업그레이드되어도 결합도가 낮아 사용자들에게 영향이 없음
			// 데이터베이스가 변경, 업그레이드 되도 사용자 입장에서는 변화가 없음  - >생산성, 유지보수성
			String sql="SELECT id,password, name, address FROM member";
			PreparedStatement pstmt = con.prepareStatement(sql); 

			// ResultSet결과의 집합이 넘어옴
			ResultSet rs = pstmt.executeQuery(); //sql실행 . 데이터베이스마다 다 각자 실행
			while(rs.next()) { //조회 결과 행이 존재하면 true //rs.next 마다 커서가 아래로 옮겨짐
				// getString(index) -> 컬럼 객체를 나타냄  // number로 설정한 경우에는 getInt, getDouble등으로 받아낼 수 있음
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
