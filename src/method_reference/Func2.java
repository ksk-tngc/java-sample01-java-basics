package method_reference;

/**
 * 関数型インターフェース2.
 *
 * <p>int型とString型を1つ受け取り、String型を返す抽象メソッドを持つ
 * 関数型インターフェース.
 */
@FunctionalInterface
public interface Func2 {
	/**
	 * 指定された引数でこの関数を実行する.
	 *
	 * @param x 関数の引数
	 * @param s 関数の引数
	 * @return 関数の結果
	 */
	String call(int x, String s);
}
