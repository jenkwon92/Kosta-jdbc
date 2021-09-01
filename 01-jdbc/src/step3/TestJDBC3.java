package step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * member id로 SELECT 
 * id 는 PRIMARY KEY (PK , 주키  기본키...)  이므로 SQL 을 실행하면 회원정보가 존재하거나 아니면 존재하지 않는다
 */
public class TestJDBC3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 회원아이디를 입력하세요 : ");
		String id = scanner.nextLine();
		scanner.close();
		System.out.println(id +" 아이디 회원정보를 db에서 조회");
		
		String driver ="oracle.jdbc.OracleDriver";
		try {
			Class.forName(driver);
			String url= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			Connection con = DriverManager.getConnection(url,"scott","tiger");
			String sql = "SELECT name,address FROM member WHERE id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			//primary key인 id로 검색했으므로 한 row 행이 존재하거나 아니면 존재하지 않으므로 if else 로 결과를 보여준다
			if(rs.next()) { //한명이 있거나 없거나
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}else {
				System.out.println(id+" 아이디를 가진 회원은 존재하지 않습니다");
			}
			rs.close();
			pstmt.close();
			con.close();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
