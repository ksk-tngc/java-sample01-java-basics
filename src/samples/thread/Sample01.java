package samples.thread;

import static samples.thread.Logger.*;

import java.util.concurrent.TimeUnit;

/**
 * スレッドのサンプルコードです。
 *
 * <p>Sapmle01：{@link Thread}クラスを継承する方法
 */
public class Sample01 {

	/**
	 * メインメソッドです。
	 *
	 * @param args
	 * @throws InterruptedException {@code join()}するスレッドが何らかの理由で中断した場合
	 */
	public static void main(String[] args) throws InterruptedException {
		log("カウントダウン開始");

		// スレッドの作成と開始
		Thread t = new PrintingThread();
		t.start();

		// スレッドの終了を待機
		t.join();

		log("カウントダウン終了");
	}

	/**
	 * {@link Thread}を継承した内部クラスです。
	 */
	private static class PrintingThread extends Thread {

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

	}

}
