package step1.unit.driver;
//step1.unit.test.TestClass에서 로딩하는 클래스
//static은 클래스 로딩만 하면 실행되고 메모리에 적재
public class MyDriver {
	private static String dbName="마이데이터베이스";
	static { //클래스가 로딩되면 실행됨
		System.out.println(dbName+"연동을 위한 초기작업");
		
	}
}
