package test;

import java.sql.SQLException;

import model.GuestBookDAO;
import model.GuestBookDTO;

public class TestGuestBookDAO3 {
	public static void main(String[] args) {
		//guestbook 에 insert(guestbook_no는 sequence로 자동발급)
		//TestGuestBookDAO1 의 방명록 글등록 기능을 업데이트
		// 글등록을 하고 나면 DB에서 발급한 글번호가 GuestBookDTO에 할당이 되어 있도록
		//registerVer2 메서드를 구현해본다(sequence의 nextval과 함께 currval을 사용한다)
		try {
			GuestBookDAO dao = new GuestBookDAO();
			GuestBookDTO dto = new GuestBookDTO("즐월요일", "즐공하자");
			System.out.println("글 등록 이전 dto : "+dto);
			dao.registVer2(dto);
			System.out.println("방명록에 글등록");
			//db에서 발급한 sequence의 nextval한 값이 dto 의 guestbookNo 인스변수에 할당되어 있다.
			System.out.println("글 등록 이후 dto : "+dto);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
