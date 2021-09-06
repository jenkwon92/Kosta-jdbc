package test;

import java.sql.SQLException;

import model.GuestBookDAO;
import model.GuestBookDTO;

public class TestGuestBookDAO1 {
	public static void main(String[] args) {
		try {
			GuestBookDAO dao = new GuestBookDAO();
			GuestBookDTO dto = new GuestBookDTO("즐월요일", "즐공하자");
			System.out.println("글 등록 이전 dto : "+dto);
			dao.regist(dto);
			System.out.println("방명록에 글등록");
			//여전히 guestbookNO는 0이고, 글 등록 후에는 db에서 발급한 sequence 즉, 방명록 글번호를 확인하고자 할 떄는
			//TestGuestBookDAO3의 시퀀스.CURRVAL 을 이용하면 된다 
			System.out.println("글 등록 이후 dto : "+dto);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
