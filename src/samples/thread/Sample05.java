package samples.thread;

import static samples.thread.Logger.*;

import java.util.concurrent.TimeUnit;

/**
 * スレッドのサンプルコードです。
 *
 * <p>Sapmle05：{@link Runnable}インターフェースの実装をメソッド参照で行う方法
 */
public class Sample05 {

	/**
	 * メインメソッドです。
	 *
	 * @param args
	 * @throws InterruptedException {@code join()}するスレッドが何らかの理由で中断した場合
	 */
	public static void main(String[] args) throws InterruptedException {
		log("カウントダウン開始");

		// スレッドの作成と開始
		// ※ 関数型インターフェース「Runnable」をメソッド参照で実装し、Threadの引数に渡す
		Thread t = new Thread(Sample05::printing);
		t.start();

		// スレッドの終了を待機
		t.join();

		log("カウントダウン終了");
	}

	/**
	 * スレッドで実行する処理です。
	 */
	private static void printing() {
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
