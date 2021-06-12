package samples.property_file.resource_bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * メインクラスです.
 *
 * <p>{@link ResourceBundle} クラスでプロパティファイルを読み込み、
 * メッセージを多言語対応するサンプルコードです.
 */
public class Main {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// デフォルトロケールの取得（JP-ja）
		Locale defLoc = Locale.getDefault();
		// ロケールに基づく現在日時を表示
		printCurrentDateBasedOnLocale(defLoc);

		// US-en ロケールの取得
		Locale usLoc = new Locale("en", "US");
		// ロケールに基づく現在日時を表示
		printCurrentDateBasedOnLocale(usLoc);
	}

	/**
	 * ロケールに基づく現在日時を表示します.
	 *
	 * <p>対象ロケールの言語コードに該当する messages.properties ファイルを
	 * {@code ResourceBundle} で読み込み、表示するメッセージを多言語対応します.
	 *
	 * <pre>
	 * e.g.
	 * 	JP-ja
	 * 	現在の時刻は 2021/06/12 21:19
	 * 	US-en
	 * 	Current time is 6/12/21, 9:19 PM
	 * </pre>
	 *
	 * <p>messages.properties は、クラスパスが通っている resources フォルダに格納しています.
	 *
	 * @param loc 対象ロケール
	 */
	private static void printCurrentDateBasedOnLocale(Locale loc) {
		// 現在のロケールを設定
		Locale.setDefault(loc);

		// 現在のロケール情報を表示
		// e.g. JP-ja
		System.out.println(loc.getCountry() + "-" + loc.getLanguage());

		// 現在のロケールで書式化された現在日時を表す文字列を取得
		// -> SimpleDateFormatのコンストラクタに書式文字列を指定しなかった場合、
		//    現在ロケールに基づく書式でフォーマットされる
		String now = new SimpleDateFormat().format(new Date());

		// 現在のロケールに該当する messages.properties ファイルを読み込む
		// -> 現在ロケールの言語コードが ja の場合、messages_ja.properties が読み込まれる
		// -> 現在のロケールに該当するファイルが存在しない場合、ファイル名に言語コードを含まない messages.properties が読み込まれる
		// -> ResourceBundle.getBundle()の引数に指定するプロパティファイルは、拡張子 .properties を省略可
		ResourceBundle rb = ResourceBundle.getBundle("messages");

		// 読み込んだプロパティファイルから、キー指定で値を取り出す
		// -> ResourceBundle#getString(キー名)
		// ロケール毎の messages.properties が使用されるため、多言語対応が可能となる
		System.out.println(rb.getString("CURRENT_TIME_IS") + " " + now);
	}
}
