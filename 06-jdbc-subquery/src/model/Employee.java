package model;

public class Employee {
	private String empNo;
	private String name;
	private String job;
	private int salary;
	
	public Employee() {
		super();
	}

	//INSERT 시에 사용한다
	public Employee(String name, String job, int salary) {
		super();
		this.name = name;
		this.job = job;
		this.salary = salary;
	}
	
	//SELECT 시에 사용한다
	public Employee(String empNo, String name, String job, int salary) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.job = job;
		this.salary = salary;
	}

	public String getEmpo() {
		return empNo;
	}

	public void setEmpo(String empNo) {
		this.empNo = empNo;
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
		return "Employee [empNo=" + empNo + ", name=" + name + ", job=" + job + ", salary=" + salary + "]";
	}
}
