package samples.web_api_call.dto.json;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Qiita WebAPI で取得したQiitaの投稿記事を表すクラスです.
 *
 * <p>JacksonでJSONを処理するためのDTOクラスです.
 */
// デシリアライズ（JSON → Javaオブジェクト変換）時、このクラスに未定義のフィールド（プロパティ）が
// JSON内に存在する場合エラーとなる。このアノテーションでそれを回避する
@JsonIgnoreProperties(ignoreUnknown = true)
public class QiitaPostItem {

	// -------------------------------------------------------------
	// フィールド
	// -------------------------------------------------------------

	/** 記事のタイトル */
	@JsonProperty("title") // マッピングするJSON側のプロパティ名。Java側フィールド名と同名の場合省略可
	public String title;

	/**
	 * 投稿日時
	 * <p>ISO-8601形式の日付文字列です.
	 * {@link #getFormatedCreatedAt()}で「yyyy/MM/dd HH:mm:ss」形式の投稿日時を取得できます.
	 */
	@JsonProperty("created_at")
	public String createdAt;

	/** いいね数 */
	@JsonProperty("likes_count")
	public int likesCount;

	/** 記事のURL */
	@JsonProperty("url")
	public String url;

	/** ユーザ情報 */
	@JsonProperty("user")
	public User user;

	/** タグ情報 */
	@JsonProperty("tags")
	public Tag[] tags;

	// -------------------------------------------------------------
	// メソッド
	// -------------------------------------------------------------

	/**
	 * このオブジェクトの文字列表現です。
	 * <p>commons-lang（Apache Commons）の {@code ToStringBuilder}を使用します.
	 *
	 * @return このオブジェクトの文字列表現
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * ISO-8601形式の「投稿日時」を「yyyy/MM/dd HH:mm:ss」形式に変換します.
	 *
	 * @return 「yyyy/MM/dd HH:mm:ss」形式の「投稿日時」
	 */
	public String getFormatedCreatedAt() {
		return getFormatedDateString(this.createdAt);
	}

	/**
	 * ISO-8601形式の日付文字列を「yyyy/MM/dd HH:mm:ss」形式に変換します.
	 *
	 * @param isoDateString ISO-8601形式の日付文字列
	 * 		<p>e.g. <pre>2021-06-18T09:33:59+09:00</pre>
	 * @return 「yyyy/MM/dd HH:mm:ss」形式の日付文字列
	 */
	private String getFormatedDateString(String isoDateString) {
		// ISO-8601形式の日付文字列は、事前定義済みのDateTimeFormatterである
		// ISO_OFFSET_DATE_TIMEでパースできる
		LocalDateTime ldt = LocalDateTime.parse(
				isoDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		return ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}

	// -------------------------------------------------------------
	// 内部クラス
	// -------------------------------------------------------------

	/**
	 * JSONの"user"プロパティに対応するクラスです.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class User {

		/** ユーザID */
		@JsonProperty("id")
		public String id;

		/** ユーザ名 */
		@JsonProperty("name")
		public String name;

		/** 投稿記事数 */
		@JsonProperty("items_count")
		public int itemsCount;

		/** ユーザ概要 */
		@JsonProperty("description")
		public String description;

		/**
		 * このオブジェクトの文字列表現です.
		 */
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	/**
	 * JSONの"tags"プロパティに対応するクラスです.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Tag {

		/** タグ名 */
		@JsonProperty("name")
		public String name;

	}

}
