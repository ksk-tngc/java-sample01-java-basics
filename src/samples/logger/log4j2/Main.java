package samples.logger.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * メインクラスです.
 *
 * <p>Log4j2 によるログ出力のサンプルコードです。
 */
public class Main {
	/**
	 * メインメソッドです.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// ロガーの取得
		Logger logger = LogManager.getLogger(Main.class);

		// ログ出力
		// Log4j2のデフォルト設定ではログレベルERROR以上しか出力されない
		// 他のログレベルも出力する場合は「log4j2.xml」に設定を記述する
		logger.trace("TRACEです");
		logger.debug("DEBUGです");
		logger.info("INFOです");
		logger.warn("WARNです");
		logger.error("ERRORです");
		logger.fatal("FATALです");

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
