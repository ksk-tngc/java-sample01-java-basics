package samples.method_reference;

/**
 * 関数型インターフェース1.
 *
 * <p>int型の引数を1つ受け取り、boolean型を返す抽象メソッドを持つ
 * 関数型インターフェース.
 */
@FunctionalInterface
public interface Func1 {
	/**
	 * 指定された引数でこの関数を実行する.
	 *
	 * @param x 関数の引数
	 * @return 関数の結果
	 */
	boolean call(int x);
}
