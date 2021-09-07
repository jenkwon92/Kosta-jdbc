package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Employee;
import model.EmployeeDAO;

public class TestEmployeeDAO2 {
	public static void main(String[] args) {
		try {
			EmployeeDAO dao = new EmployeeDAO();
			String job="개발";
			/*
			 * 	 해당 job (직종) 의 사원 중 가장 높은 salary를 받는
			 * 사원 정보를 조회하는 메서드
			 */
			ArrayList<Employee> list = dao.getEmployeeListByHighSalAndJob(job);
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
