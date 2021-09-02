package test2_inst;

import java.sql.SQLException;



/*
 * 회원정보를 member TABLE에 INSERT하는 예제
 */
public class TestMemberDAO1 {
	public static void main(String[] args) {
		try {
			MemberDAO dao = new MemberDAO();
			//INSERT 즉, 등록할 회원 정보
			MemberVO vo = new MemberVO("servlet","a","이상순","애월읍");
			if(dao.findMemberById(vo.getId())!=null) {
				System.out.println("아이디가 존재하여 등록불가");
			}else {
				dao.registerMember(vo);
				System.out.println("등록완료");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
