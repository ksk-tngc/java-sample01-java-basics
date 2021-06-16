package samples.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * {@code URL}クラスを用いてWebサイトのHTMLを取得するサンプルです.
 */
public class Main {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// URLクラスをインスタンス化
		URL url = new URL("http://abehiroshi.la.coocan.jp/prof/prof.htm");

		// openStream()でストリームを取得
		// 取得するのはHTMLなので、文字データを扱いやすくするため、バイトストリーム（InputStream）を
		// 文字ストリーム（InputStreamReader）に変換
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "SJIS");

		// ストリームから1文字ずつ読み込み、表示
		int i;
		while ((i = isr.read()) != -1) {
			System.out.print((char) i);
		}
	}

}
