package test2_inst;

import java.sql.SQLException;
import java.util.Scanner;

public class TestMemberDAO2 {
	public static void main(String[] args) {
		try {
			MemberDAO dao = new MemberDAO();
			Scanner scanner = new Scanner(System.in);
			System.out.print("검색할 회원 아이디를 입력하세요 : ");
			String id= scanner.nextLine();
			scanner.close();
			MemberVO memberVO = dao.findMemberById(id);
			if(memberVO != null) {
				System.out.println("검색결과 : "+memberVO.toString());
			}else {
				System.out.println(id+ "아이디를 가진 회원정보 없음");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
