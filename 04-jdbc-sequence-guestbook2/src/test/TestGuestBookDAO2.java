package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookDTO;

public class TestGuestBookDAO2 {
	public static void main(String[] args) {
		try {
			GuestBookDAO dao = new GuestBookDAO();
			//최근 글부터 출력되도록 조회한다 (guestbook_no는 sequence로 자동발급)
			ArrayList<GuestBookDTO> list = dao.getAllGuestBookList();
			for(int i=0; i<list.size();i++)
				System.out.println(list.get(i));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
