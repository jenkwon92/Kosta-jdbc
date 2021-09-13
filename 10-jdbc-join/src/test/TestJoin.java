package test;

import java.sql.SQLException;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestJoin {
	public static void main(String[] args) {
		try {
			EmployeeDAO dao = new EmployeeDAO();
			/*
			 * 사원번호가 1인 사원의 사원명ename, 월급 sql , 부서번호 deptno, 부서명 dname, 근무지 loc, 전화번호 tel 를 조회하여 출력한다 
			 * 즉, 사원테이블과 부서테이즐의 정보를 결합해서 (join) 조회
			 */
			int empno=1;
			EmployeeVO vo = dao.findEmployeeByEmpno(empno);
			if(vo!=null) {
				System.out.println("사원번호:"+vo.getEmpNo());
				System.out.println("사원명:"+vo.getEname());
				System.out.println("월급: "+vo.getSal());
				System.out.println("부서번호 : "+vo.getDepartmentVO().getDeptno());
				System.out.println("부서명 : "+vo.getDepartmentVO().getDname());
				System.out.println("근무지 : "+vo.getDepartmentVO().getLoc());
				System.out.println("부서전화번호 : "+vo.getDepartmentVO().getTel());
			}else {
				System.out.println(empno+"사원번호에 해당하는 사원이 존재하지 않습니다");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
