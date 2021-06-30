package samples.assertion;

/**
 * アサーションのサンプルクラス{@link Account}のドライバです。
 *
 * <p>アサーション({@code assert}文)を有効にするには、
 * {@code java}コマンドに{@code -ea}オプションが必要です。
 */
public class Main {

	/**
	 * メインメソッドです。
	 * @param args
	 */
	public static void main(String[] args) {
		// 口座を用意
		Account myAcnt = new Account("Me", 10000);
		Account destAcnt = new Account("Tom", 20000);

		// 送金処理
		// 残高がマイナスになるため、AssertionErrorでVMが停止する
		myAcnt.transfer(destAcnt, 10001);

		System.out.println("正常終了");
	}

}
