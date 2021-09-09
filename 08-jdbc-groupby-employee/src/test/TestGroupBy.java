package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.EmployeeDAO;

public class TestGroupBy {
	public static void main(String[] args) {
		try {
			EmployeeDAO dao = new EmployeeDAO();
			/*
			 * s_employee 의 job 변 최고 salary를 조회( 최고salary 내림차순)
			 * 
			 * 총무 1100
			 * 개발 900
			 * 영업 700
			 * 
			 * SELECT job, COUNT(*) AS 사원수 ,MAX(salary) AS 최고월급
			 * FROM s_employee 
			 * GROUP BY job 
			 * ORDER BY 최고월급 DESC; 
			 * 
			 * job: 총무	사원수: 5명	최고월급: 1100
			 * job: 개발	사원수:3명	최고월급:900
			 * job:영업	사원수:2명	최고월급:700
			 */
			ArrayList<HashMap<String,Object>> list = dao.findJobGroupList();
			for(int i=0; i<list.size(); i++) {
				HashMap<String, Object> map = list.get(i);
				System.out.println("job:"+map.get("job")+ " 사원 수:"+map.get("empcount")+"명 최고월급:"+map.get("highestsal"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
