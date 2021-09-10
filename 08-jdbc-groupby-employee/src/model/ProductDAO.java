package model;import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductDAO {
	private String driver ="oracle.jdbc.OracleDriver";
	private String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String username="scott";
	private String password ="tiger";
	
	public ProductDAO() throws ClassNotFoundException  {
		Class.forName(driver);
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con !=null)
			con.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs !=null)
			rs.close();
		closeAll(pstmt, con);
	}
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
	public ArrayList<HashMap<String, Object>> findProductGroupLessThanAvgPrice() throws SQLException {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con = getConnection();
			//String sql ="SELECT maker,ROUND(AVG(price)) AS avgprice FROM product GROUP BY maker HAVING ROUND(AVG(price))<(SELECT ROUND(AVG(price)) FROM product) ORDER BY avgprice DESC";
			StringBuilder sql = new StringBuilder("SELECT maker,ROUND(AVG(price)) AS avgprice ");
			sql.append("FROM product ");
			sql.append("GROUP BY maker ");
			sql.append("HAVING ROUND(AVG(price))<(SELECT ROUND(AVG(price)) FROM product) ");
			sql.append("ORDER BY avgprice DESC");
			//pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("maker", rs.getString(1));
				map.put("avgprice", rs.getInt(2));
				list.add(map);
			}	
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	
}
