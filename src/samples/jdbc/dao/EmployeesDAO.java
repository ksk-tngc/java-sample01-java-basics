package samples.jdbc.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import samples.jdbc.dto.Employee;

/**
 * EMPLOYEESテーブルのDAOです.
 */
public class EmployeesDAO {

	// -------------------------------------------------------------------
	// 定数
	// -------------------------------------------------------------------

	/** ロガー（SLF4J + Logback） */
	private static final Logger logger = LoggerFactory.getLogger(EmployeesDAO.class);

	/** H2 Database のJDBCドライバ（ドライバクラスのFQCN） */
	private static final String JDBC_DRIVER_FQCN = "org.h2.Driver";

	/** JDBC URL（接続文字列） */
	private static final String JDBC_URL_EMBEDDED = "jdbc:h2:./db/sample_db"; // 組み込みモード（同時接続不可）
	private static final String JDBC_URL_SERVER = "jdbc:h2:tcp://localhost//" // サーバモード（同時接続可）
			+ new File("").getAbsolutePath() // プロジェクトの絶対パス
			+ "/db/sample_db"; // DBファイル

	// -------------------------------------------------------------------
	// コンストラクタ
	// -------------------------------------------------------------------

	/**
	 * デフォルトコンストラクタです.
	 *
	 * <p>H2 Database のJDBCドライバをロードします.
	 *
	 * @throws ClassNotFoundException JDBCドライバのロードに失敗した場合
	 */
	public EmployeesDAO() throws ClassNotFoundException {
		try {
			// H2 Database のJDBCドライバをロード（有効化）
			Class.forName(JDBC_DRIVER_FQCN);
			logger.info("JDBCドライバをロードしました。");
		} catch (ClassNotFoundException e) {
			logger.error("JDBCドライバのロードに失敗しました。");
			throw e;
		}
	}

	// -------------------------------------------------------------------
	// メソッド
	// -------------------------------------------------------------------

	/**
	 * EMPLOYEESテーブルに1件登録します.
	 *
	 * @param emp EMPLOYEESテーブルのDTO
	 * @return INSERTの件数
	 * @throws SQLException DBアクセスでエラーが発生した場合
	 */
	public int create(Employee emp) throws SQLException {
		Connection con = null;
		try {
			// データベース接続
			con = DriverManager.getConnection(JDBC_URL_SERVER);
			logger.info("DBに接続しました。");

			// 自動コミットOFF
			con.setAutoCommit(false);

			// SQL文生成
			// EMPLOYEE_IDはDB側で自動採番
			String sql = "INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, SALARY, HIRE_DATE) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			// パラメータセット
			pstmt.setString(1, emp.getFirstName()); // FIRST_NAME
			pstmt.setString(2, emp.getLastName()); // LAST_NAME
			pstmt.setInt(3, emp.getSalary()); // SALARY
			pstmt.setDate(4, Employee.convertToSqlDate(emp.getHireDate())); // HIRE_DATE

			// SQL実行
			int r = pstmt.executeUpdate();
			logger.info("INSERT文を実行しました。件数：" + r);

			// コミット
			con.commit();

			// INSERTの件数
			return r;

		} catch (SQLException e) {
			// DB接続失敗時やSQLエラー時
			logger.error("DBアクセスでエラーが発生しました。");
			try {
				// ロールバック
				con.rollback();
				logger.info("ロールバックしました。");
			} catch (SQLException e1) {
				logger.error("ロールバックに失敗しました。");
				throw e1;
			}
			// 再スロー
			throw e;

		} finally {
			if (con != null) {
				try {
					// データベース接続を切断（finallyで確実に閉じる）
					con.close();
					logger.info("DB接続を切断しました。");
				} catch (SQLException e) {
					logger.error("DB接続の切断に失敗しました。");
					throw e;
				}
			}
		}
	}

	/**
	 * EMPLOYEESテーブルから指定のEMPLOYEE_IDのレコードを削除します.
	 *
	 * @param empId 削除対象の社員ID
	 * @return DELETEの件数
	 * @throws SQLException DBアクセスでエラーが発生した場合
	 */
	public int deleteByEmployeeId(int empId) throws SQLException {
		Connection con = null;
		try {
			// データベース接続
			con = DriverManager.getConnection(JDBC_URL_SERVER);
			logger.info("DBに接続しました。");

			// 自動コミットOFF
			con.setAutoCommit(false);

			// SQL文生成
			String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			// パラメータセット
			pstmt.setInt(1, empId); // EMPLOYEE_ID

			// SQL実行
			int r = pstmt.executeUpdate();
			logger.info("DELETE文を実行しました。件数：" + r);

			// コミット
			con.commit();

			// DELETEの件数
			return r;

		} catch (SQLException e) {
			// DB接続失敗時やSQLエラー時
			logger.error("DBアクセスでエラーが発生しました。");
			try {
				// ロールバック
				con.rollback();
				logger.info("ロールバックしました。");
			} catch (SQLException e1) {
				logger.error("ロールバックに失敗しました。");
				throw e1;
			}
			// 再スロー
			throw e;

		} finally {
			if (con != null) {
				try {
					// データベース接続を切断（finallyで確実に閉じる）
					con.close();
					logger.info("DB接続を切断しました。");
				} catch (SQLException e) {
					logger.error("DB接続の切断に失敗しました。");
					throw e;
				}
			}
		}
	}

	/**
	 * LAST_NAMEをキーに、EMPLOYEESテーブルを検索します.
	 *
	 * @param lastName EMPLOYEES.LAST_NAME（検索キー）
	 * @return 検索結果（Employeeリスト）
	 * @throws SQLException DBアクセスでエラーが発生した場合
	 */
	public List<Employee> findByLastName(String lastName) throws SQLException {
		Connection con = null;
		try {
			// データベース接続
			con = DriverManager.getConnection(JDBC_URL_SERVER);
			logger.info("DBに接続しました。");

			// SQL文生成
			String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, HIRE_DATE "
					+ "FROM EMPLOYEES "
					+ "WHERE LAST_NAME LIKE ? ";
//			PreparedStatement pstmt = con.prepareStatement(sql);
			PreparedStatement pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, // 順方向にも逆方向にもスクロール可能なResultSetを使用可能にするための定数
					ResultSet.CONCUR_READ_ONLY); // 更新できないResultSetを示す定数

			// パラメータセット
			pstmt.setString(1, "%" + lastName + "%"); // LAST_NAME

			// SQL実行
			ResultSet rs = pstmt.executeQuery();

			// 件数を取得
			// ResultSetに件数（行数）を返すメソッドは用意されていないため、
			// 最後の行に移動し、行番号を取得する
			// beforeFirst()で逆方向にスクロールするには、ConnectionからPreparedStatementを取得する際に
			// "TYPE_SCROLL_INSENSITIVE"の指定が必要
			rs.last();
			int count = rs.getRow();
			rs.beforeFirst();
			logger.info("SELECT文を実行しました。件数：" + count);

			// ArrayListに格納
			List<Employee> emps = new ArrayList<>();
			while (rs.next()) { // イテレータ同様、現在の行をnext()で進めつつ処理する
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setSalary(rs.getInt("SALARY"));
				emp.setHireDate(Employee.convertToUtilDate(rs.getDate("HIRE_DATE"))); // sql.Date -> util.Date 変換
				emps.add(emp);
			}

			// Employeeリストを返却
			return emps;

		} catch (SQLException e) {
			// DB接続失敗時やSQLエラー時
			logger.error("DBアクセスでエラーが発生しました。");
			throw e;

		} finally {
			if (con != null) {
				try {
					// データベース接続を切断（finallyで確実に閉じる）
					con.close();
					logger.info("DB接続を切断しました。");
				} catch (SQLException e) {
					logger.error("DB接続の切断に失敗しました。");
					throw e;
				}
			}
		}
	}

}
