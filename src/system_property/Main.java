package system_property;

import java.util.Properties;

/**
 * メインクラス.
 *
 * <p>システムプロパティのサンプルコード.
 */
public class Main {
	/**
	 * メインメソッド.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// ----------------------------------------------------
		// システムプロパティのキーから値を取得
		// ----------------------------------------------------
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));

		// ----------------------------------------------------
		// システムプロパティの一覧を表示
		// ----------------------------------------------------
		// システムプロパティを取得
		Properties props = System.getProperties();

		// システムプロパティの一覧を表示
		// Properties#stringPropertyNames()でキーのSetを取得し、
		// キー名でソート後、キーと値を表示する
		props.stringPropertyNames().stream()
				.sorted()
				.map(key -> key + " = " + System.getProperty(key))
				.forEach(str -> System.out.println(str));

		// ----------------------------------------------------
		// 環境に依存しない改行コードの使用
		// ----------------------------------------------------
		// いずれもOSの改行コードが取得される
		final String BR = System.getProperty("line.separator");
		final String BR2 = System.lineSeparator();

		System.out.println("AAA" + BR + "BBB");
		System.out.println("CCC" + BR2 + "DDD");
	}
}
