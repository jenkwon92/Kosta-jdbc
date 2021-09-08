package test;

import model.AccountDAO;

public class TestUnit3 {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			String accountNo = "11"; //존재하지 않는 계좌번호 테스트
			String password="1"; //패스워드테스트
			int money =0; //입금액 테스트
			dao.deposit(accountNo,password,money);
			//비즈니스 적인 예외시에 getMessage()를 남긴다.
		} catch (ClassNotFoundException e) { //시스템적인 오류
			e.printStackTrace();
		}
	}
}
