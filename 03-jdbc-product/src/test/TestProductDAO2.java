package test;

import java.sql.SQLException;

import model.ProductDAO;

public class TestProductDAO2 {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			int productId =1; //존재하는아이디 test
			productId = 7; //존재하지 않는 아이디 test
			boolean result = dao.existsById(productId);
			if(result)
				System.out.println(productId+" 아이디 상품이 존재합니다");
			else
				System.out.println(productId +" 아이디 상품이 존재하지 않습니다");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
