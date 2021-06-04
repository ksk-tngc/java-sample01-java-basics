package samples.commons_lang;

import java.time.LocalDate;

/**
 * {@code commons-lang} を使用しない {@code equals()} と {@code toString()} の
 * 実装サンプルです.
 *
 * @see PersonUsingCommonsLang
 */
public class Person {

	// ------------------------------------------------------------
	// フィールド
	// ------------------------------------------------------------

	/** 氏名 */
	private String fullName;
	/** 生年月日 */
	private LocalDate birthday;
	/** メールアドレス */
	private String emailAddr;

	// ------------------------------------------------------------
	// コンストラクタ
	// ------------------------------------------------------------

	/**
	 * すべてのフィールドを初期化するコンストラクタです.
	 *
	 * @param fullName 氏名
	 * @param birthday 生年月日
	 * @param emailAddr メールアドレス
	 */
	public Person(String fullName, LocalDate birthday, String emailAddr) {
		this.fullName = fullName;
		this.birthday = birthday;
		this.emailAddr = emailAddr;
	}

	// ------------------------------------------------------------
	// メソッド
	// ------------------------------------------------------------

	/**
	 * 等価評価を行います.
	 * すべてのフィールドが等価なら等価とみなします.
	 *
	 * <p>典型的な記述による等価評価のサンプルです.<br>
	 * {@code commons-lang} を使用すると1行で記述できます.
	 *
	 * @param obj 比較対象のオブジェクト
	 * @return このインスタンスと引数 {@code obj} が等価ならtrue
	 *
	 * @see PersonUsingCommonsLang#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// 同一インスタンスなら等価
		if (this == obj) {
			return true;
		}
		// nullなら等価でない
		if (obj == null) {
			return false;
		}
		// 型が違えば等価でない
		if (!(obj instanceof Person)) {
			return false;
		}

		// 比較対象をキャスト
		Person p = (Person) obj;

		// 等価評価
		// すべてのフィールドが等価なら等価とみなす
		// 氏名
		if (!(this.fullName.trim().equals(p.fullName.trim()))) {
			return false;
		}
		// 生年月日
		if (!(this.birthday.equals(p.birthday))) {
			return false;
		}
		// メールアドレス
		if (!(this.emailAddr.trim().equals(p.emailAddr.trim()))) {
			return false;
		}

		return true;
	}

	/**
	 * インスタンスの文字列表現です.
	 *
	 * <p>{@code commons-lang} を使用しない {@code toString()} の実装サンプルです.
	 *
	 * @return このインスタンスの文字列表現
	 *
	 * @see PersonUsingCommonsLang#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"fullName=%s, birthday=%s, emailAddr=%s", fullName, birthday, emailAddr);
	}
}
