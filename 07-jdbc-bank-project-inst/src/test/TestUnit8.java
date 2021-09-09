package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.AccountDAO;
import model.AccountVO;

public class TestUnit8 {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			ArrayList<AccountVO>  list= dao.findHighestBalanceAccount();
			for(AccountVO vo:list)
				System.out.println(vo.getAccountNo()+" "+vo.getName()+" "+vo.getBalance());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
