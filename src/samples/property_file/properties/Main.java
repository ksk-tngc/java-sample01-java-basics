package samples.property_file.properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メインクラスです.
 *
 * <p>{@link Properties} クラスでプロパティファイルの読み書きを行うサンプルコードです.
 */
public class Main {

	/** ロガー（SLF4J + Logback） **/
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	/** プロパティファイルのパス **/
	private static final String PROP_FILE_PATH = "resources/pref.properties";

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// プロパティファイルの書き込み
		writePropertiesFile();
		// プロパティファイルの読み込み
		readPropertiesFile();
	}

	/**
	 * プロパティファイルの書き込みサンプルです.
	 */
	private static void writePropertiesFile() {
		// 開始ログ
		logger.info("--- writePropertiesFile()：開始 ---");

		try (
				// プロパティファイル書き込み用ストリームをオープン
				Writer fw = new FileWriter(PROP_FILE_PATH);
		) {
			// Propertiesインスタンスを生成
			Properties p = new Properties();

			// キーと値のペアをセットする
			p.setProperty("tokyo.capital", "東京");
			p.setProperty("tokyo.food", "寿司");
			p.setProperty("aichi.capital", "名古屋");
			p.setProperty("aichi.food", "味噌カツ");

			// プロパティファイルに書き込む
			p.store(fw, null);

			// 終了ログ
			logger.info("--- writePropertiesFile()：終了 ---");

		} catch (IOException e) {
			logger.error("プロパティファイルの書き込みに失敗しました。", e);
		}
	}

	/**
	 * プロパティファイルの読み込みサンプルです.
	 */
	private static void readPropertiesFile() {
		// 開始ログ
		logger.info("--- readPropertiesFile()：開始 ---");

		try (
				// プロパティファイル読み込み用ストリームをオープン
				Reader fr = new FileReader(PROP_FILE_PATH);
		) {
			// プロパティファイルを読み込む
			Properties p = new Properties();
			p.load(fr);

			// -------------------------------------------------------------
			// キーを指定して値を取り出す
			// -------------------------------------------------------------
			// Properties#getProperty() -> 戻り値はString
			System.out.println(p.getProperty("aichi.capital"));
			System.out.println(p.getProperty("aichi.food"));

			// -------------------------------------------------------------
			// すべてのキーを取り出して表示する
			// -------------------------------------------------------------
			// Properties#stringPropertyNames()はキーが格納されたSet<String>を返す
			// Setなのでキー名は順不同
			for (String key : p.stringPropertyNames()) {
				System.out.println(key);
			}

			// StreamAPIで記述した場合
			p.stringPropertyNames().stream()
					.forEach(key -> System.out.println(key));
			p.stringPropertyNames().stream()
					.forEach(System.out::println);

			// -------------------------------------------------------------
			// すべてのキーを取り出し、キーと値のペアを表示する
			// -------------------------------------------------------------
			// Properties#stringPropertyNames()はキーが格納されたSet<String>を返す
			// Setなのでキー名は順不同
			for (String key : p.stringPropertyNames()) {
				System.out.println(
						String.format("key: %s = value: %s", key, p.getProperty(key)));
			}

			// StreamAPIで記述した場合
			// キー名を自然順序付けでソートする
			p.stringPropertyNames().stream()
					.sorted() // 自然順序付けでキーをソート
					.map(key -> String.format("key: %s = value: %s", key, p.getProperty(key))) // 出力文字列を整形
					.forEach(s -> System.out.println(s));

			// -------------------------------------------------------------
			// Properties#list()を使用して、すべてのキーと値を表示する
			// -------------------------------------------------------------
			p.list(System.out);

			// 終了ログ
			logger.info("--- readPropertiesFile()：終了 ---");

		} catch (IOException e) {
			logger.error("プロパティファイルの読み込みに失敗しました。", e);
		}
	}
}
