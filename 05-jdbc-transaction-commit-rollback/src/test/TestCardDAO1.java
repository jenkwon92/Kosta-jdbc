package test;

import java.sql.SQLException;

import model.CardDAO;

public class TestCardDAO1 {
	public static void main(String[] args)  {
		try {
			CardDAO dao = new CardDAO();
			dao.registerCardAndPoint("java", "아이유", "cgv", 10000);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
