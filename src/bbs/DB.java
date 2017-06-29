package bbs;

import java.sql.*;

public class DB {
	public static final String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DBURL = "jdbc:sqlserver://localhost:1433;DatabaseName=BBS";
	public static final String LOGINNAME = "sa";
	public static final String LOGINPWD = "zxc1234";
	
	public static Connection getConn() {
		Connection conn = null;
		
		try {
			Class.forName(DRIVERNAME);
			conn = DriverManager.getConnection(DBURL, LOGINNAME, LOGINPWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return conn;
	}
	
	public static Statement getStmt(Connection conn) {
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stmt;
	}
	
	public static ResultSet executeQuery(Statement stmt,String sql) {
		ResultSet rs = null;
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn = null;
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			stmt = null;
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rs = null;
		}
	}
}