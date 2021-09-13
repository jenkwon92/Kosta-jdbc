package model;

public class EmployeeVO {
	private int empNo;
	private String ename;
	private int sal;
	//has a 관계로 표현
	private DepartmentVO departmentVO;
	
	public EmployeeVO() {
		super();
	}
	
	public EmployeeVO(int empNo, String ename, int sal, DepartmentVO departmentVO) {
		super();
		this.empNo = empNo;
		this.ename = ename;
		this.sal = sal;
		this.departmentVO = departmentVO;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	@Override
	public String toString() {
		return "EmployeeVO [empNo=" + empNo + ", ename=" + ename + ", sal=" + sal + ", departmentVO=" + departmentVO
				+ "]";
	}
}
