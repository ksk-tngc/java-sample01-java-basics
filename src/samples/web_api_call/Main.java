package samples.web_api_call;

import java.util.ArrayList;
import java.util.List;

import samples.web_api_call.dto.json.QiitaPostItem;

/**
 * Qiita WebAPI の{@code https://qiita.com/api/v2/items}エンドポイントより
 * 最新の投稿記事を取得するサンプルコードです.
 */
public class Main {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Qiita WebAPI で最新記事を取得
		QiitaWebAPI qiita = new QiitaWebAPI();
		QiitaPostItem[] items = qiita.getItems("java+web+api", 5); // 検索キーワードと取得する記事の件数を指定

		// 最新記事の情報を表示
		for (QiitaPostItem item : items) {
			System.out.println("---------------------------------------------");
			System.out.println("記事タイトル: " + item.title);
			System.out.println("投稿日時: " + item.getFormatedCreatedAt());
			System.out.println("いいね数: " + item.likesCount);
			// タグ名をStringリストに変換
			List<String> tagList = new ArrayList<>();
			for (QiitaPostItem.Tag tag : item.tags) {
				tagList.add(tag.name);
			}
			System.out.println("タグ: " + String.join(", ", tagList)); // カンマ区切りで表示
			System.out.println("記事URL: " + item.url);
			System.out.println("ユーザID: " + item.user.id);
			System.out.println("ユーザ名: " + item.user.name);
			System.out.println("投稿記事数: " + item.user.itemsCount);
			System.out.println("概要: " + item.user.description);
		}
	}

}
