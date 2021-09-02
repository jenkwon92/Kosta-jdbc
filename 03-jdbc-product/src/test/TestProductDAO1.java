package test;

import java.sql.SQLException;

import model.ProductDAO;

public class TestProductDAO1 {
	public static void main(String[] args) {
		//총 상품수 조회
			try {
				ProductDAO dao = new ProductDAO();
				//hint : ResultSet 의 getInt(column Index)를 이용한다
				int totalCount = dao.getProductTotalCount();
				System.out.println("총 상품수 :  "+totalCount);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
