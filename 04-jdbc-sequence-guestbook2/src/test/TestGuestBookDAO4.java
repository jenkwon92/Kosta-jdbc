package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookDTO;

public class TestGuestBookDAO4 {
	public static void main(String[] args) {
		//LIKE 연산자를 이용해서 방명록 글제목(title) 을 이용해 정보를 조회하는 기능을 구현
		try {
			GuestBookDAO dao = new GuestBookDAO();
			String keyword ="즐";
			//keyword ="월";
			ArrayList<GuestBookDTO> list = dao.getGuestBookListLikeKeyword(keyword);
			
			  for(int i=0; i<list.size(); i++) { System.out.println(list.get(i)); }
			 
			//아래와 같은 방식으로도 for loop를 실행할 수 있다.
			/*
			 * for(GuestBookDTO dto:list) //list에 있는 모든 요소들을 순차적으로 dto에 할당하면서 반복한다
			 * System.out.println(dto);
			 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
