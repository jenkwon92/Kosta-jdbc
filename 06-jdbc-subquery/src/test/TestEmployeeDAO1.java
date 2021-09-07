package test;

import java.sql.SQLException;

import model.Employee;
import model.EmployeeDAO;

public class TestEmployeeDAO1 {
	public static void main(String[] args) {
		try {
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = new Employee("황의조","총무",900);
			dao.register(emp);
			System.out.println("사원 등록 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
