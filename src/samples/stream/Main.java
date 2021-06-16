package samples.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * メインクラスです.
 *
 * <p>ストリームに関するサンプルコードです.<br>
 * 文字列（String）をバイトストリームや文字ストリームに変換します.
 */
public class Main {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// ------------------------------------------------------
		// 文字列（String）をバイトストリーム（InputStream）に変換する例
		// ------------------------------------------------------

		// 文字列
		String s = "あいうえお";
//		String s = "ABCDE";

		// 文字列 → InputStream 変換
		// String#getBytes()で文字列をバイト配列に変換し、
		// InputStreamの具象クラスであるByteArrayInputStreamを生成
		ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());

		// 1バイトずつ読み込んで、charに変換し、表示
		// InputStreamのread()は「1バイト」を返すメソッドなので、
		// ★ 日本語等の2バイト以上の文字は扱えない（charにキャストしても文字化けする）
		int i;
		while ((i = bais.read()) != -1) {
			System.out.println((char) i);
		}

		System.out.println("--------------------------");

		// ------------------------------------------------------
		// 文字列（String）を文字ストリーム（Reader）に変換する例 ①
		// ------------------------------------------------------

		// 読み込みを終えている（マーク位置が最後まで達している）ByteArrayInputStreamのマーク位置を0位置に戻す
		// read()する度にマーク位置は動く
		bais.reset();

		// バイトストリーム → 文字ストリーム 変換
		// バイトストリームと文字ストリームの橋渡し役であるInputStreamReader（Readerの具象クラス）に
		// InputStream（ByteArrayInputStream）を渡し、バイトストリームを文字ストリームに変換
		InputStreamReader isr = new InputStreamReader(bais);

		// 1「文字」ずつ読み込んで、charに変換し、表示
		// ★ InputStreamReaderは文字ストリームなので、read()の戻り値は1文字を表すバイト表現となり、
		// 日本語等の2バイト以上の文字をcharにキャストしても文字化けしない
		while ((i = isr.read()) != -1) {
			System.out.println((char) i);
		}

		System.out.println("--------------------------");

		// ------------------------------------------------------
		// 文字列（String）を文字ストリーム（Reader）に変換する例 ② ★
		// ------------------------------------------------------

		// 文字列 → 文字ストリーム 変換
		// Readerの具象クラスであるStringReaderに文字列を渡し、文字列を文字ストリームに変換
		StringReader sr = new StringReader(s);

		// 1「文字」ずつ読み込んで、charに変換し、表示
		// ★ StringReaderは文字ストリームなので、read()の戻り値は1文字を表すバイト表現となり、
		// 日本語等の2バイト以上の文字をcharにキャストしても文字化けしない
		while ((i = sr.read()) != -1) {
			System.out.println((char) i);
		}

		System.out.println("--------------------------");
	}

}
