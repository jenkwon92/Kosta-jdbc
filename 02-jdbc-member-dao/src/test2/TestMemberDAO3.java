package test2;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestMemberDAO3 {
	public static void main(String[] args) {

		try {
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberVO> list = dao.getAllMemberList();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("id : " + list.get(i).getId() + " name : " + list.get(i).getName());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
