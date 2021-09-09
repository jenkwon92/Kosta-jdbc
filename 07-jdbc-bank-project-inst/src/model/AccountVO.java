package model;

public class AccountVO {
	private String accountNo;
	private String name;
	private String password;
	private int balance;
	public AccountVO() {
		super();		
	}
	//오버로딩 규칙: 순서, 타입 갯수 달라야 하므로 아래와 같이 순서를 변경하면 오버로딩이 된다
		public AccountVO( String accountNo, int balance,String name) {
			super();
			this.accountNo = accountNo;
			this.name = name;
			this.balance = balance;
		}
	public AccountVO(String name, String password, int balance) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
	}
	public AccountVO(String accountNo, String name, String password, int balance) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.password = password;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", name=" + name + ", password=" + password + ", balance="
				+ balance + "]";
	}
	
}
