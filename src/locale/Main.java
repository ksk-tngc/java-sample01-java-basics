package locale;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * メインクラス.
 *
 * <p>{@link Locale}クラスのサンプルコード.
 *
 * <p>ロケールの言語によって現在日時の表示内容を切り替える.
 * 本サンプルでは「JP-ja」と「US-en」を使用.
 */
public class Main {

	/**
	 * メインメソッド.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// ----------------------------------------------
		// デフォルトロケール（JP-ja）
		// ----------------------------------------------

		// デフォルトロケールの取得（OSのロケール -> JP-ja）
		Locale defLoc = Locale.getDefault();

		// ロケールの情報を表示
		printLocaleInfo(defLoc);

		// ロケールの言語による現在日時の表示
		printCurrentDateTime(defLoc.getLanguage());

		// ----------------------------------------------
		// USロケール（US-en）
		// ----------------------------------------------

		// USロケールの生成
		Locale usLoc = new Locale("en", "US");

		// ロケールの情報を表示
		printLocaleInfo(usLoc);

		// ロケールの言語による現在日時の表示
		printCurrentDateTime(usLoc.getLanguage());

	}

	/**
	 * ロケールの国と言語情報を表示する.
	 *
	 * @param loc 表示対象のロケール
	 */
	private static void printLocaleInfo(Locale loc) {

		// ロケールの国情報を表示
		System.out.println(
				String.format("ロケールの国情報：%s（%s）", loc.getCountry(), loc.getDisplayCountry()));

		// ロケールの言語情報を表示
		System.out.println(
				String.format("ロケールの言語情報：%s（%s）", loc.getLanguage(), loc.getDisplayLanguage()));

	}

	/**
	 * ロケールの言語コードによる現在日時の表示処理.
	 *
	 * @param langCode 表示内容を切り替えるための言語コード
	 */
	private static void printCurrentDateTime(String langCode) {

		// 現在のロケールでフォーマットされた日時文字列を取得
		// SimpleDateFormatのコンストラクタに日付フォーマットを指定しなかった場合、
		// 現在のロケールの日付フォーマットが使用される.
		String now = new SimpleDateFormat().format(new Date());

		// 言語コードにより表示内容を切り替える
		if (langCode.equals("ja")) {
			System.out.println("現在日時は " + now + " です");
		} else {
			System.out.println("Current Data Time is " + now + ".");
		}

	}

}
