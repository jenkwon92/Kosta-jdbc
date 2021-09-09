package model;

public interface DbInfo {
	//인터페이스이므로 아래 field 는 public static final로 인식된다 
	String DRIVER="oracle.jdbc.OracleDriver";
	String URL="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String USERNAME="scott";
	String PASSWORD="tiger";
}
