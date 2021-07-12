package samples.thread;

import static samples.thread.Logger.*;

import java.util.concurrent.TimeUnit;

/**
 * スレッドのサンプルコードです。
 *
 * <p>Sapmle02：{@link Thread}クラスを継承する方法（匿名クラスを使用）
 */
public class Sample02 {

	/**
	 * メインメソッドです。
	 *
	 * @param args
	 * @throws InterruptedException {@code join()}するスレッドが何らかの理由で中断した場合
	 */
	public static void main(String[] args) throws InterruptedException {
		log("カウントダウン開始");

		// スレッドの作成と開始（匿名クラスを使用）
		Thread t = new Thread() {
			/**
			 * {@link Thread#run()}をオーバーライドし、
			 * このスレッドで実行する処理を定義します。
			 */
			@Override
			public void run() {
				for (int i = 10; i >= 1; i--) {
					log(i + "..");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();

		// スレッドの終了を待機
		t.join();

		log("カウントダウン終了");
	}

}
