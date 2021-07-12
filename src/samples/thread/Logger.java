package samples.thread;

import java.time.LocalTime;

/**
 * ログ出力クラスです。
 */
public class Logger {

	/**
	 * スレッド名付きのログをコンソールに出力します。
	 *
	 * @param msg ログに出力するメッセージ
	 */
	public static void log(String msg) {
		System.out.println(LocalTime.now()
				+ "(" + Thread.currentThread().getName() + ") "
				+ msg);
	}

}
