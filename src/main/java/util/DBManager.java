package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource source = (DataSource) init.lookup("java:comp/env/jdbc/GYMUnity");

			conn = source.getConnection();

			System.out.println("DB연결 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("DB연결 오류");
		}

		return conn;
	}

	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("DB 종료 오류");
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("DB 종료 오류");
		}
	}

}
