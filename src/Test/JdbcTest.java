package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JdbcTest {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:sqlserver://172.24.34.60:1803;DatabaseName=DEVCONFIGUREDB_HQ";
		String user = "CSDP_IVR_TEST";
		String password = "CSDP_IVR_TEST#1";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement stmt = conn.createStatement();
		conn.setAutoCommit(false);
		
		ArrayList<String> sqlList = new ArrayList<String>();
		for (int i = 0; i < 9; i++) {
			sqlList.add("insert into AAA_Test(val) values('" + i + "')");
		}
		sqlList.add("insert into AA_Test(val) values('" + 123 + "')");
		try {
			int count = 0;
			int[] result;
			for (int i = 0; i < sqlList.size(); i++) {
				stmt.addBatch(sqlList.get(i));
				count++;
				if (count >= 10) {
					result = stmt.executeBatch();
					conn.commit();
					stmt.clearBatch();
					count = 0;
				}
			}
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(conn != null)
			System.out.println("連線成功!");
		else
			System.out.println("連線失敗");
		conn.close();
	}
}
