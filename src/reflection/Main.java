package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * メインクラスです。
 *
 * <p>リフレクションAPIのサンプルコードです。<br>
 * {@link String}クラスの型情報と、全てのフィールド名・メソッド名を表示します。
 */
public class Main {
	/**
	 * メインメソッドです。
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// -----------------------------------------------
		// Stringの型情報を表示
		// -----------------------------------------------
		System.out.println("----- Stringの型情報 -----");

		Class<?> clazz = String.class; // Stringの型情報（Classインスタンス）を取得
		System.out.println("FQCN: " + clazz.getName()); // java.lang.String
		System.out.println("クラス名: " + clazz.getSimpleName()); // String
		System.out.println("パッケージ名: " + clazz.getPackageName()); // java.lang
		System.out.println("配列かどうか: " + clazz.isArray()); // false
		System.out.println("親クラスのFQCN: " + clazz.getSuperclass().getName()); // java.lang.Object

		// -----------------------------------------------
		// Stringクラスが持つ全てのフィールド名を表示
		// -----------------------------------------------
		System.out.println("----- フィールド -----");

		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			System.out.println(f.getName());
		}

		// -----------------------------------------------
		// Stringクラスが持つ全てのメソッド名を表示
		// -----------------------------------------------
		System.out.println("----- メソッド -----");

		Method[] ms = clazz.getDeclaredMethods();
		Arrays.stream(ms)
				.map(m -> m.getName()) // メソッド名を取得
				.sorted() // 自然順序でソート
				.distinct() // オーバーロードされたメソッド（同名メソッド）の重複除去
				.forEach(System.out::println); // 表示
	}
}
