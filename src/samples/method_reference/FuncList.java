package samples.method_reference;

/**
 * 関数として扱うメソッドを持つクラス.
 */
public class FuncList {
	/**
	 * 奇数かどうかを判定するメソッド.
	 *
	 * @param x 判定対象の数値
	 * @return 奇数ならtrue
	 */
	public static boolean isOdd(int x) {
		return (x % 2 == 1);
	}

	/**
	 * 合否判定の結果文字列を返すメソッド.
	 *
	 * @param point 点数
	 * @param name 指名
	 * @return 合否判定を表す文字列
	 */
	public String passCheck(int point, String name) {
		return name + "さんは" + (point > 65 ? "合格" : "不合格");
	}
}
