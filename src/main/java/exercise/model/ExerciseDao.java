package exercise.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ExerciseDao {
	private static ExerciseDao instance = new ExerciseDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ExerciseDao() { }

	public static ExerciseDao getInstance() {
		return instance;
	}
	
	public List<ExerciseResponseDto> findExerciseAll(ExerciseRequestDto exerciseDto) {
		List<ExerciseResponseDto> exercises = new ArrayList<>();
		String sql = "SELECT exercise_index, exer.exercise_category_index, exer_cate.name, exer.user_code, users.id, users.name, exer.name, content, exer.create_date, exer.mod_date "
				+ "FROM exercises AS exer "
				+ "JOIN exercise_categories as exer_cate ON exer_cate.exercise_category_index = exer.exercise_category_index "
				+ "JOIN users ON users.code = exer.user_code "
				+ "ORDER BY create_date DESC";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int categoryIndex = rs.getInt(2);
				String categoryName = rs.getString(3);

				int userCode = rs.getInt(4);
				String userId = rs.getString(5);
				String userName = rs.getString(6);

				String name = rs.getString(7);
				String content = rs.getString(8);
				Timestamp createDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);
				
				exercises.add(new ExerciseResponseDto(index, categoryIndex, categoryName, userCode, userId, userName, name, content, createDate, modDate));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exercises;
	}

	public List<ExerciseResponseDto> findExerciseAllByQuery(ExerciseRequestDto exerciseDto, String query) {
		List<ExerciseResponseDto> exercises = new ArrayList<>();
		String sql = "SELECT exercise_index, exer.exercise_category_index, exer_cate.name, exer.user_code, users.id, users.name, exer.name, content, exer.create_date, exer.mod_date " +
				" FROM exercises AS exer "
				+ "JOIN exercise_categories as exer_cate ON exer_cate.exercise_category_index = exer.exercise_category_index "
				+ "JOIN users ON users.code = exer.user_code "
				+ "WHERE exer_cate.name LIKE ? "
				+ "OR exer.name LIKE ? "
				+ "OR users.id LIKE ? "
				+ "OR users.name LIKE ? "
				+ "OR content LIKE ? "
				+ "ORDER BY create_date DESC";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + query + "%");
			pstmt.setString(3, "%" + query + "%");
			pstmt.setString(4, "%" + query + "%");
			pstmt.setString(5, "%" + query + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1);
				int categoryIndex = rs.getInt(2);
				String categoryName = rs.getString(3);

				int userCode = rs.getInt(4);
				String userId = rs.getString(5);
				String userName = rs.getString(6);

				String name = rs.getString(7);
				String content = rs.getString(8);
				Timestamp createDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);

				exercises.add(new ExerciseResponseDto(index, categoryIndex, categoryName, userCode, userId, userName, name, content, createDate, modDate));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return exercises;
	}

	public ExerciseResponseDto findExerciseOneByIndex(ExerciseRequestDto exerciseDto) {
		ExerciseResponseDto exercise = null;
		String sql = "SELECT exercise_index, exer.exercise_category_index, exer_cate.name, exer.user_code, users.id, users.name AS \"user_name\", exer.name, content, exer.create_date, exer.mod_date "
				+ "FROM exercises AS exer "
				+ "JOIN exercise_categories as exer_cate ON exer_cate.exercise_category_index = exer.exercise_category_index "
				+ "JOIN users ON users.code = exer.user_code "
				+ "WHERE exer.exercise_index = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getIndex());
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int index = rs.getInt(1);
				int categoryIndex = rs.getInt(2);
				String categoryName = rs.getString(3);

				int userCode = rs.getInt(4);
				String userId = rs.getString(5);
				String userName = rs.getString(6);

				String name = rs.getString(7);
				String content = rs.getString(8);
				Timestamp createDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);

				exercise = new ExerciseResponseDto(index, categoryIndex, categoryName, userCode, userId, userName, name , content, createDate, modDate);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return exercise;
	}

	public boolean createExercise(ExerciseRequestDto exerciseDto) {
		boolean isAdded = true;
		String sql = "INSERT INTO exercises (exercise_category_index, user_code, name, content) "
					+ "VALUES (?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getCategoryIndex());
			pstmt.setInt(2, exerciseDto.getUserCode());
			pstmt.setString(3, exerciseDto.getName());
			pstmt.setString(4, exerciseDto.getContent());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isAdded = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isAdded;
	}

	public boolean deleteExercise(ExerciseRequestDto exerciseDto) {
		boolean isDeleted = true;
		String sql = "DELETE FROM exercises "
					+ "WHERE exercise_index = ? AND user_code = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getIndex());
			pstmt.setInt(2, exerciseDto.getUserCode());
			
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isDeleted;
	}

	public boolean updateExercise(ExerciseRequestDto exerciseDto) {
		boolean isUpdated = true;
		String sql = "UPDATE exercises "
					+ "SET exercise_category_index = ?, name = ?, content = ?"
					+ "WHERE exercise_index = ? AND user_code = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getCategoryIndex());
			pstmt.setString(2, exerciseDto.getName());
			pstmt.setString(3, exerciseDto.getContent());
			pstmt.setInt(4, exerciseDto.getIndex());
			pstmt.setInt(5, exerciseDto.getUserCode());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			isUpdated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isUpdated;
	}

	public boolean isExerciseWriter(ExerciseRequestDto exerciseDto) {
		boolean isWriter = false;

		String sql = "SELECT * FROM exercises "
					+ "WHERE user_code = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, exerciseDto.getUserCode());

			rs = pstmt.executeQuery();

			if (rs.next())
				isWriter = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isWriter;
	}
}