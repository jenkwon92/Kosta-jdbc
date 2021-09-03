package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductDAO;
import model.ProductDTO;

public class TestProductDAO5 {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			int highPrice = 1700;
			int lowPrice = 1300;
			//lowPrice 이상 highPrice 이하 
			ArrayList<ProductDTO> list = dao.findProductByPriceOrderByPriceDesc(lowPrice, highPrice);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
