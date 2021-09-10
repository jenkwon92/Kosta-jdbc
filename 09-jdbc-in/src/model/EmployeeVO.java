package model;

public class EmployeeVO {
	private int empno;
	private String name;
	private String job;
	private int salary;
	public EmployeeVO(int empno, String name, String job, int salary) {
		super();
		this.empno = empno;
		this.name = name;
		this.job = job;
		this.salary = salary;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmployeeVO [empno=" + empno + ", name=" + name + ", job=" + job + ", salary=" + salary + "]";
	}
	
	
}
