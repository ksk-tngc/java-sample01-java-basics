package method_reference;

import java.util.function.IntPredicate;

/**
 * メインクラス.
 *
 * <p>関数型インターフェースに関数オブジェクト（メソッド参照）を
 * 格納し、関数を実行するクラス.
 */
public class Main {
	/**
	 * メインメソッド.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// 自作の関数型インターフェースを型とする変数に関数オブジェクト（メソッド参照）を代入
		Func1 f1 = FuncList::isOdd; // staticメソッドなので「クラス名::メソッド名」
		Func2 f2 = new FuncList()::passCheck; // インスタンスメソッドなので「インスタンス::メソッド名」

		// 標準関数インターフェースを使用
		// IntPredicateは1つのint型を受け取り、booleanを返す関数型インターフェース
		IntPredicate f3 = FuncList::isOdd;

		// 関数の実行（FuncList.isOdd）
		System.out.println(f1.call(1));
		System.out.println(f1.call(2));

		// 関数の実行（FuncList.isOdd）
		System.out.println(f3.test(1));
		System.out.println(f3.test(2));

		// 関数の実行（FuncList#passCheck）
		System.out.println(f2.call(65, "Aさん"));
		System.out.println(f2.call(66, "Bさん"));
	}
}
