package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestIN {
	public static void main(String[] args) {
		try {
			EmployeeDAO dao = new EmployeeDAO();
			/*
			  	s_employee 테이블에 저장된 전체 사원 정보의 평균월급 (salary) 보다 
				직종별(job) 평균월급이 낮은 직종(job)에 해당하는 사원 정보를 조회
			 */
			ArrayList<EmployeeVO> list = dao.findEmployeeList();
			for(EmployeeVO vo:list)
				System.out.println(vo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}













