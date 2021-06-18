package samples.web_api_call;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import samples.web_api_call.dto.json.QiitaPostItem;

/**
 * Qiita WebAPI を呼び出すメソッドを提供するクラスです.
 */
public class QiitaWebAPI {

	/**
	 * 最新記事を取得します.
	 *
	 * <p>Qiita WebAPI の{@code https://qiita.com/api/v2/items}エンドポイントより
	 * 最新の投稿記事を取得し、{@link QiitaPostItem}配列として返却します.
	 *
	 * @param keyword 検索キーワード
	 * @param count 取得する記事の件数（1〜100）
	 * @return Qiita投稿記事を表す{@link QiitaPostItem}の配列
	 * @throws Exception 記事取得処理に失敗した場合
	 */
	public QiitaPostItem[] getItems(String keyword, int count) throws Exception {
		// QiitaAPIエンドポイントURL
		final String QIITA_API_ENDPOINT_URL = "https://qiita.com/api/v2/items";
		// 取得するページ番号
		final String PAGE_NUMBER = "1";

		// GETリクエストのクエリパラメータ
		String queryParam = String.format(
				"?query=%s&per_page=%s&page=%s",
				keyword, count, PAGE_NUMBER);

		// 引数チェック
		if (count < 1 || count > 100) {
			throw new IllegalArgumentException("引数countは1〜100の範囲で指定して下さい。");
		}

		// GETリクエストを送信し、レスポンスを取得
		// 取得するレスポンスボディは文字列表現
		HttpResponse<String> res = sendGetRequest(QIITA_API_ENDPOINT_URL, queryParam);

		// HTTPリクエストとレスポンスの情報を表示
		// デバッグ用
//		printRequestAndResponseInfo(res.request(), res);

		// レスポンスボディ（JSON）からQiitaPostItem配列を取得
		QiitaPostItem[] items = deserializeJsonToQiitaPostItems(res.body());

		// Qiita投稿記事の配列
		return items;
	}

	/**
	 * 指定のWebAPIエンドポイントにGETリクエストを送信し、レスポンスを取得します.
	 *
	 * <p>レスポンスボディはJSON形式が期待されます（HTTPリクエストヘッダのAcceptは"application/json"です）.
	 *
	 * @param url GETリクエストを送信するWebAPIエンドポイントURL
	 * @param queryParam URLに追加するクエリパラメータ
	 * @return HTTPレスポンス（レスポンスボディは文字列表現です）
	 * @throws Exception リクエストの送信又はレスポンスの受信に失敗した場合
	 */
	private HttpResponse<String> sendGetRequest(String url, String queryParam) throws Exception {
		// HttpClientインスタンス生成
		// HTTP通信（リクエスト送信とレスポンス取得）の中心的役割を担うクラス
		// newBuilder()でBuilderを取得し、各種の設定メソッドを連鎖的に呼び出し、
		// 最後にbuild()を呼び出し、でHttpClientを生成する
		HttpClient client = HttpClient.newBuilder()
				.version(Version.HTTP_1_1) // HTTP1.1を使用
				.followRedirects(Redirect.NORMAL) // 30X応答で転送先に自動訪問
				.build(); // HttpClient生成

		// HttpRequestインスタンス生成
		// newBuilder()でBuilderを取得し、HTTPリクエストの内容を各種の設定メソッドで設定し、
		// 最後にbuild()を呼び出し、HttpRequestを生成する
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create(url + queryParam)) // エンドポイントURL + クエリパラメータ
				.header("Accept", "application/json") // リクエストヘッダ（レスポンスで期待する応答形式）
				.GET() // メソッド指定
				.build(); // HttpRequest生成

		HttpResponse<String> res;
		try {
			// リクエストの送信とレスポンスの取得
			// HttpClient#send()でリクエストを送信し、レスポンスを取得する
			// 非同期方式でリクエスト送信する場合はHttpClient#sendAsync()を使用する
			res = client.send(req,
					BodyHandlers.ofString()); // 同期方式でリクエスト。レスポンスは文字列として取得
		} catch (IOException | InterruptedException e) {
			throw new Exception("リクエストの送信又はレスポンスの受信に失敗しました。", e);
		}

		// HTTPレスポンス
		return res;
	}

	/**
	 * 実行後のHTTPリクエストとレスポンスの情報を表示します.
	 *
	 * @param req HTTPリクエスト
	 * @param res HTTPレスポンス（レスポンスボディは文字列表現です）
	 */
	private void printRequestAndResponseInfo(HttpRequest req, HttpResponse<String> res) {
		// HTTPリクエストとレスポンスの情報を表示
		System.out.println("----- request -----");
		System.out.println("http version: " +
				req.version() // 戻り値はOptional。HttpRequestにHTTPのバージョンが設定されていない場合、Optionalは空
						.orElse(res.version())); // Optionalが空の場合は、HttpResponseから取得する
		System.out.println("url: " + req.uri()); // リクエストしたURL
		System.out.println("method: " + req.method()); // リクエストメソッド

		System.out.println("----- response -----");
		System.out.println("status code: " + res.statusCode());
		System.out.println("body: ");
		System.out.println(res.body());
	}

	/**
	 * Qiita投稿記事（JSON）をJavaオブジェクトに変換します.
	 *
	 * @param json Qiita投稿記事（JSON）
	 * @return JSONから変換された{@link QiitaPostItem}配列
	 * @throws Exception JSONのパースに失敗した場合
	 */
	private QiitaPostItem[] deserializeJsonToQiitaPostItems(String json) throws Exception {
		// JSONをJavaオブジェクトに変換するJacksonのマッパー
		ObjectMapper mapper = new ObjectMapper();

		QiitaPostItem[] items;
		try {
			// JSONをJavaオブジェクトに変換（デシリアライズ）
			items = mapper.readValue(json, QiitaPostItem[].class);
		} catch (JsonProcessingException e) {
			throw new Exception("JSONパースに失敗しました。", e);
		}

		// Qiita投稿記事の配列
		return items;
	}

}
