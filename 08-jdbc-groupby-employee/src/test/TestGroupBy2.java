package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.ProductDAO;

public class TestGroupBy2 {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			//전체 product 의 평균가 보다 그룹maker 별 평균가가 낮은 그룹(maker)의 maker명과 평균가를 조회(내림차순)
			ArrayList <HashMap<String,Object>> list = dao.findProductGroupLessThanAvgPrice();
			for(int i=0; i<list.size(); i++) {
				HashMap<String,Object> map = list.get(i);
				System.out.println("maker:"+map.get("maker") +" avgprice:"+map.get("avgprice"));
			}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
