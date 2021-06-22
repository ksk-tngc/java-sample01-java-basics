package samples.jdbc.dto;

import java.util.Date;

/**
 * EMPLOYEESテーブルのDTOです.
 */
public class Employee {

	// -------------------------------------------------------------------
	// フィールド
	// -------------------------------------------------------------------

	/** 社員ID（DB側で自動採番） */
	private int employeeId;

	/** 名 */
	private String firstName;

	/** 姓 */
	private String lastName;

	/** 給料 */
	private int salary;

	/** 雇用日 */
	private Date hireDate;

	// -------------------------------------------------------------------
	// コンストラクタ
	// -------------------------------------------------------------------

	/**
	 * デフォルトコンストラクタです.
	 */
	public Employee() {
	}

	/**
	 * すべてのフィールドを初期化するコンストラクタです.
	 *
	 * @param firstName 名
	 * @param lastName 姓
	 * @param salary 給料
	 * @param hireDate 雇用日（java.util.Date）
	 */
	public Employee(String firstName, String lastName, int salary, Date hireDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.hireDate = hireDate;
	}

	// -------------------------------------------------------------------
	// メソッド
	// -------------------------------------------------------------------

	/**
	 * java.util.Date -> java.sql.Date 変換処理です.
	 *
	 * @param date {@code java.util.Date}型のDateオブジェクト
	 * @return {@code java.sql.Date}型のDateオブジェクト
	 */
	public static java.sql.Date convertToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * java.sql.Date -> java.util.Date 変換処理です.
	 *
	 * @param date {@code java.sql.Date}型のDateオブジェクト
	 * @return {@code java.util.Date}型のDateオブジェクト
	 */
	public static Date convertToUtilDate(java.sql.Date date) {
		return new Date(date.getTime());
	}

	// -------------------------------------------------------------------
	// Getter / Setter
	// -------------------------------------------------------------------

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

}
