package samples.commons_lang;

import java.time.LocalDate;

/**
 * {@link Person} と {@link PersonUsingCommonsLang} の
 * 実行クラスです.
 */
public class Main {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// ----------------------------------------
		// Personクラス
		// ----------------------------------------
		Person p1 = new Person("Aさん", LocalDate.of(1981, 10, 1), "p1@mail.com");
		Person p2 = new Person("Aさん", LocalDate.of(1981, 10, 1), "p1@mail.com");
		Person p3 = new Person("Bさん", LocalDate.of(1981, 10, 1), "p1@mail.com");

		// equals()
		System.out.println(p1.equals(p2)); // true
		System.out.println(p2.equals(p3)); // false

		// toString()
		System.out.println(p1); // fullName=Aさん, birthday=1981-10-01, emailAddr=p1@mail.com

		// ----------------------------------------
		// PersonUsingCommonsLangクラス
		// ----------------------------------------

		PersonUsingCommonsLang pc1 = new PersonUsingCommonsLang("Aさん", LocalDate.of(1981, 10, 1), "p1@mail.com");
		PersonUsingCommonsLang pc2 = new PersonUsingCommonsLang("Aさん", LocalDate.of(1981, 10, 1), "p1@mail.com");
		PersonUsingCommonsLang pc3 = new PersonUsingCommonsLang("Bさん", LocalDate.of(1981, 10, 1), "p1@mail.com");

		// equals()
		System.out.println(pc1.equals(pc2)); // true
		System.out.println(pc2.equals(pc3)); // false

		// toString()
		System.out.println(pc1); // PersonUsingCommonsLang[birthday=1981-10-01,emailAddr=p1@mail.com,fullName=Aさん]
	}
}
