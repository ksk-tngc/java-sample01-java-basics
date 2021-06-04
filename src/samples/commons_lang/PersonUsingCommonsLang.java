package samples.commons_lang;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * {@code commons-lang} の {@link EqualsBuilder} と {@link ToStringBuilder} を
 * 使用したサンプルクラスです.
 *
 * @see Person
 */
public class PersonUsingCommonsLang {

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
	public PersonUsingCommonsLang(String fullName, LocalDate birthday, String emailAddr) {
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
	 * <p>{@code commons-lang} の {@link EqualsBuilder} を使用したサンプルです.
	 *
	 * @param obj 比較対象のオブジェクト
	 * @return このインスタンスと引数 {@code obj} が等価ならtrue
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * インスタンスの文字列表現です.
	 *
	 * <p>{@code commons-lang} の {@link ToStringBuilder} を使用したサンプルです.
	 *
	 * @return このインスタンスの文字列表現
	 */
	@Override
	public String toString() {
		// 第2引数にtoString()のスタイルを指定
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
