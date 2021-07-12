package samples.thread;

import static samples.thread.Logger.*;

import java.util.concurrent.TimeUnit;

/**
 * スレッドのサンプルコードです。
 *
 * <p>Sapmle03：{@link Runnable}インターフェースの実装クラスを{@link Thread}に渡す方法
 */
public class Sample03 {

	/**
	 * メインメソッドです。
	 *
	 * @param args
	 * @throws InterruptedException {@code join()}するスレッドが何らかの理由で中断した場合
	 */
	public static void main(String[] args) throws InterruptedException {
		log("カウントダウン開始");

		// スレッドの作成と開始
		// ※ Runnableの実装クラスをThreadに渡す
		Thread t = new Thread(new PrintingThread());
		t.start();

		// スレッドの終了を待機
		t.join();

		log("カウントダウン終了");
	}

	/**
	 * {@link Runnable}を実装した内部クラスです。
	 */
	private static class PrintingThread implements Runnable {

		/**
		 * {@link Runnable#run()}をオーバーライドし、
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
