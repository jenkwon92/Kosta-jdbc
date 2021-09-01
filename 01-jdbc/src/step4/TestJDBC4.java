package step4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * id에 해당하는 회원 정보를 삭제
 * 
 * 스캐너를 이용해 아이디를 입력받은 후 아이디에 해당하는 회원을 삭제
 * 삭제에 성공하면 executeUpdate()의 반환값이 1이 반환 -> ~~id 회원 정보를 삭제
 * 아이디에 해당하는 회원이 없어서 삭제하지 못하면 execueUpdate() 의 반환값이 0이 반환 -> ~~ id에 해당하는 
 * 회원이 없어서 삭제하지 못했습니다
 */
public class TestJDBC4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("삭제할 회원의 아이디를 입력하세요 : ");
		String id = scanner.nextLine();
		scanner.close();
		System.out.println(id+" 아이디의 회원을 삭제합니다");
		
		String driver ="oracle.jdbc.OracleDriver";
		String url= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,"scott","tiger");
			String sql = "DELETE FROM member WHERE id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate(); //result : 삭제한 행 row의 수 반환
			if(result == 1)
				System.out.println(id +" 아이디 회원정보를 삭제");
			else
				System.out.println(id+" 아이디 회원정보가 존재하지 않아 삭제하지 못했습니다");
			
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}








