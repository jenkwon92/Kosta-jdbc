package test;

import java.sql.SQLException;

import model.ProductDAO;
import model.ProductDTO;
//상품정보를 등록하는 
public class TestProductDAO3 {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			ProductDTO dto = new ProductDTO("5","카스","OB",1700);
			if(dao.existsById(Integer.parseInt(dto.getId()))) {
				System.out.println("상품이 존재하여 등록불가");
			}else {
				dao.register(dto);
				System.out.println("상품등록");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
