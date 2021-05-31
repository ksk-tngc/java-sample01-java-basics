package lambda_expression;

import java.util.function.BiFunction;
import java.util.function.IntPredicate;

/**
 * メインクラス.
 *
 * <p>ラムダ式で関数を即時実装するサンプルコード.
 *
 * <p>ラムダ式は関数型インターフェースの抽象メソッドを即時実装する記法.<br>
 * このサンプルでは標準関数インターフェースの{@link IntPredicate}と{@link BiFunction}を使用し、
 * 関数を即時実装する.
 */
public class Main {
	/**
	 * メインメソッド.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		lambdaSample1();
		lambdaSample2();
	}

	/**
	 * ラムダ式サンプル1.
	 */
	private static void lambdaSample1() {
		// 奇数かどうかを判定する関数をラムダ式で定義
		// IntPredicateは1つのint型を受け取り、booleanを返す関数型インターフェース
		IntPredicate isOdd = (x) -> {
			return (x % 2 == 1);
		};

		// 上記を省略記法で記述
		IntPredicate isOdd2 = x -> x % 2 == 1;

		// 上記を匿名クラスで記述
		IntPredicate isOdd3 = new IntPredicate() {
			@Override
			public boolean test(int x) {
				return (x % 2 == 1);
			}
		};

		System.out.println(isOdd.test(3));
		System.out.println(isOdd2.test(3));
		System.out.println(isOdd3.test(3));
	}

	/**
	 * ラムダ式サンプル2.
	 */
	private static void lambdaSample2() {
		// 合否判定の結果文字列を返す関数をラムダ式で定義
		// BiFunctionは2つの引数を受け取り、結果を返す関数型インターフェース
		BiFunction<Integer, String, String> passCheck = (point, name) -> {
			return name + "さんは" + (point > 65 ? "合格" : "不合格");
		};

		// 上記を省略記法で記述
		BiFunction<Integer, String, String> passCheck2 = (point, name) -> name + "さんは" + (point > 65 ? "合格" : "不合格");

		// 上記を匿名クラスで記述
		BiFunction<Integer, String, String> passCheck3 = new BiFunction<>() {
			@Override
			public String apply(Integer point, String name) {
				return name + "さんは" + (point > 65 ? "合格" : "不合格");
			}
		};

		System.out.println(passCheck.apply(66, "Aさん"));
		System.out.println(passCheck2.apply(66, "Bさん"));
		System.out.println(passCheck3.apply(66, "Cさん"));
	}
}
