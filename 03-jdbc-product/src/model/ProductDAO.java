package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//Data Access Object
public class ProductDAO {
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String username = "scott";
	private String password = "tiger";

	public ProductDAO() throws ClassNotFoundException {
		Class.forName(driver);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	public int getProductTotalCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT COUNT(*) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) // 조회된 결과가 있으면 true를 반환하고 결과 행으로 cursor를 이동한다
				totalCount = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}

	public boolean existsById(int productId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean result = false;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT COUNT(*) FROM product WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			// 풀이 1
			/*
			 * if(rs.next()) { if(rs.getInt(1)==1) result =true; }
			 */
			// 풀이 2
			if (rs.next() && rs.getInt(1) == 1)
				result = true;
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

	public void register(ProductDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO product (id,name,maker,price) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getMaker());
			pstmt.setInt(4, dto.getPrice());

			dto = new ProductDTO();
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}

	}

	public ArrayList<String> getMakerKindList() throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url,username,password);
			String sql = "SELECT DISTINCT maker FROM product "; 
			pstmt  = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public ArrayList<ProductDTO> findProductByPriceOrderByPriceDesc(int lowPrice, int highPrice) throws SQLException {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			con = DriverManager.getConnection(url,username,password);
			//String sql  = "SELECT id,name,maker,price  FROM product WHERE price BETWEEN ? AND ? ORDER BY price DESC";
			//String sql = "SELECT id, name,maker,price FROM product WHERE price>=? AND price<=?"; 
			
			//SQL이 길어지면 StringBuilder로 넣어서 붙여주는 것이 좋은 방식이다
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, name, maker, price ");
			sql.append("FROM product ");
			sql.append("WHERE price >= ? AND price <= ? ");
			sql.append("ORDER BY price DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, lowPrice);
			pstmt.setInt(2, highPrice);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new ProductDTO(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
}
