package samples.assertion;

/**
 * 口座を表すクラスです。
 */
public class Account {

	// ------------------------------ フィールド ------------------------------ //

	/** 名義人 */
	public String ownerName;

	/** 残高 */
	public int balance;

	// ------------------------------ コンストラクタ ------------------------------ //

	/**
	 * フィールドを初期化してインスタンス化するコンストラクタです。
	 *
	 * @param ownerName 名義人
	 * @param balance 残高
	 */
	public Account(String ownerName, int balance) {
		this.ownerName = ownerName;
		this.balance = balance;
	}

	// ------------------------------ メソッド ------------------------------ //

	/**
	 * 送金処理です。
	 *
	 * @param dest 送金先の口座
	 * @param amount 送金する金額
	 */
	public void transfer(Account dest, int amount) {
		this.balance -= amount;
		dest.balance += amount;

		// アサーションで異常事態を検知する
		// 残高は0円以上であること！
		assert this.balance >= 0 : String.format("残高が異常 (balance=%d)", this.balance);
	}

}
