package samples.file_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メインクラスです.
 *
 * <p>ファイル入出力に関するサンプルコードです.<br>
 * 読み込んだファイルをGZIP圧縮するプログラムです.
 */
public class Main {
	/**
	 * メインメソッドです.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// ロガーの取得（SLF4J + Logback）
		final Logger logger = LoggerFactory.getLogger(Main.class);

		// 入力ファイルのパス
		// このクラス（Main.class）と同じパッケージ内にある dummy.txt の絶対パスを取得
		final String IN_FILE_PATH = Main.class.getResource("dummy.txt").getPath();

		// 出力ファイルのパス
		final String OUT_FILE_PATH = "out/dummy.gz";

		// try-with-resources文を使用することで、tryブロックを抜ける際に
		// 自動的にclose()処理が実行される（オートクローズ）
		try (
				// 入力ファイルをオープン
				// - FileInputStream -> バイナリファイル読み込み用ストリーム
				// - BufferedInputStream -> パフォーマンス向上のためのバッファリングフィルタ
				FileInputStream fis = new FileInputStream(IN_FILE_PATH);
				BufferedInputStream bis = new BufferedInputStream(fis);

				// 出力ファイルをオープン
				// - FileOutputStream -> バイナリファイル書き込み用ストリーム
				// - BufferedOutputStream -> パフォーマンス向上のためのバッファリングフィルタ
				// - GZIPOutputStream -> GZIP圧縮用ストリームフィルタ
				FileOutputStream fos = new FileOutputStream(OUT_FILE_PATH);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				GZIPOutputStream gos = new GZIPOutputStream(bos);
		) {
			// 開始ログ
			logger.info("ファイル圧縮処理を開始します");
			logger.info("入力ファイル: " + IN_FILE_PATH);
			logger.info("出力ファイル: " + OUT_FILE_PATH);

			// 入力ファイルを1バイトずつ読み込みつつGZIPファイルを出力する
			int b;
			while ((b = bis.read()) != -1) {
				// GZIP圧縮用ストリームフィルタに書き込む
				gos.write(b);
			}

			// close()の前にflush()し、確実にファイルを書き込む
			gos.flush();

			// 終了ログ
			logger.info("処理完了");

		} catch (IOException e) {
			logger.error("ファイル入出力で例外が発生しました。", e);
		}
	}
}
