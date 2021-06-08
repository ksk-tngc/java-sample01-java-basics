package samples.logger.slf4j_logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メインクラスです.
 *
 * <p>SLF4J + Logback によるログ出力のサンプルコードです。
 * ロギングにおけるインターフェース（ログファサード）が SLF4J、
 * 実装（バインディング）が Logback です。
 *
 * <p>{@code logback-classic-xxx.jar} と {@code logback-core-xxx.jar} を
 * クラスパスに追加することで SLF4J のバインディングとして Logback が使用されます。
 */
public class Main {
	/**
	 * メインメソッドです.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// ロガーの取得
		Logger logger = LoggerFactory.getLogger(Main.class);

		// ログ出力
		// Logbackのデフォルト設定ではログレベルDEBUG以上しか出力されない
		// TRACEを出力する場合は「logback.xml」に設定を記述する
		logger.trace("TRACEです"); // デフォルトでは出力されない
		logger.debug("DEBUGです");
		logger.info("INFOです");
		logger.warn("WARNです");
		logger.error("ERRORです");

		// プレースホルダの使用
		// "{}"がプレースホルダ。第2引数以降は可変長引数でここに値を指定する
		logger.info("info log: UserId={}, Name={}", 1234, "山田");

		// スタックトレースをログに出力
		try {
			String s = null;
			s.length();
		} catch (Exception e) {
			logger.error("error log: ", e); // 第2引数にThrowableを指定
		}
	}
}
