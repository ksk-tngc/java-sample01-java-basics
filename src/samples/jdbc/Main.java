package samples.jdbc;

import java.text.SimpleDateFormat;
import java.util.List;

import samples.jdbc.dao.EmployeesDAO;
import samples.jdbc.dto.Employee;

/**
 * JDBCでDBアクセスするサンプルコードです.
 * DBMSは H2 Database を使用します.
 */
public class Main {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// EMPLOYEESテーブルのDAO
		EmployeesDAO empDao = new EmployeesDAO();

		// 登録するレコード（EMPLOYEESテーブルのDTO）
		Employee emp = new Employee("太郎", "テスト", 400000,
				new SimpleDateFormat("yyyy/MM/dd").parse("2021/06/21"));

		// EMPLOYEESテーブルに1件登録（INSERT）
		empDao.create(emp);

		// EMPLOYEESテーブルから1件削除（DELETE）
		empDao.deleteByEmployeeId(2);

		// EMPLOYEESテーブルから取得（SELECT）
		List<Employee> emps = empDao.findByLastName("テスト");
		for (Employee e : emps) {
			System.out.println("---------------------------------------");
			System.out.println("EMPLOYEE_ID: " + e.getEmployeeId());
			System.out.println("FIRST_NAME: " + e.getFirstName());
			System.out.println("LAST_NAME: " + e.getLastName());
			System.out.println("SALARY: " + e.getSalary());
			System.out.println("HIRE_DATE: " + new SimpleDateFormat("yyyy/MM/dd").format(e.getHireDate()));
		}
	}

}
