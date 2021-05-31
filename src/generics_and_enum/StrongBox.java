package generics_and_enum;

/**
 * 金庫を表すクラスです。
 *
 * <p>任意の型のインスタンスを1つ保持できます。
 * 格納するインスタンスの型は金庫クラスのインスタンス生成時に実型引数で指定します。
 *
 * <p>格納には {@link #put(Object) put()} を、取得には {@link #get()} を使用します。
 *
 * <p>コンストラクタで「鍵の種類」（ダイヤル式・指紋認証など）を指定し、
 * 鍵の種類ごとに定められた必要試行回数に達するまで金庫に格納したインスタンスは取得不可となります。
 *
 * @param <E> 金庫クラスに格納するインスタンスの型
 */
public class StrongBox<E> {

	// ----------------------------------------------------
	// フィールド
	// ----------------------------------------------------

	/** 金庫が保持するデータ */
	private E data;

	/** 金庫の鍵の種類 */
	private KeyType keyType;

	/** 現在の試行回数 */
	private int currentTrials;

	// ----------------------------------------------------
	// 列挙型
	// ----------------------------------------------------

	/**
	 * 鍵の種類を定義した列挙型です。
	 */
	public enum KeyType {

		/** 列挙子（鍵の種類） */
		PADLOCK("南京錠", 1_024), BUTTON("押しボタン", 10_000), DIAL("ダイヤル", 30_000), FINGER("指紋認証", 1_000_000),;

		/** 列挙子（鍵）の日本語表記 （final宣言とし、コンストラクタでの初期化を強制する） */
		private final String name;

		/** 金庫を解除するための必要試行回数 */
		private final int trials;

		/**
		 * 列挙型のコンストラクタです。（private宣言）
		 *
		 * @param name 列挙子（鍵）の日本語表記
		 * @param trials 金庫を解除するための必要試行回数
		 */
		private KeyType(String name, int trials) {
			this.name = name;
			this.trials = trials;
		}

		/**
		 * toString()のオーバーライドです。
		 *
		 * @return 列挙子（鍵）の日本語表記
		 */
		@Override
		public String toString() {
			return this.name;
		}

		/**
		 * 金庫を解除するための必要試行回数を返します。
		 *
		 * @return 必要試行回数
		 */
		public int getTrials() {
			return this.trials;
		}

	}

	// ----------------------------------------------------
	// コンストラクタ
	// ----------------------------------------------------

	/**
	 * コンストラクタです。
	 * 金庫に適用する鍵の種類を指定します。
	 *
	 * @param keyType 鍵の種類
	 */
	public StrongBox(KeyType keyType) {
		this.keyType = keyType;
	}

	// ----------------------------------------------------
	// メソッド
	// ----------------------------------------------------

	/**
	 * 金庫にインスタンスを格納します。
	 *
	 * @param data 金庫に格納するインスタンス
	 */
	public void put(E data) {
		this.data = data;
	}

	/**
	 * 金庫からインスタンスを取得します。
	 *
	 * <p>このメソッドが呼び出されるたびに回数をカウントし、
	 * 鍵の種類ごとに定められた必要試行回数に到達しない限りnullを返します。
	 *
	 * @return 金庫に格納されているデータ
	 */
	public E get() {

		// 現在の試行回数をカウントアップ
		this.currentTrials++;

		// 必要試行回数に到達していない場合
		if (this.currentTrials < this.keyType.getTrials()) {
			return null;
		}

		return this.data;

	}

	// ----------------------------------------------------
	// getter / setter
	// ----------------------------------------------------

	/**
	 * 現在の試行回数のgetterです。
	 *
	 * @return 現在の試行回数
	 */
	public int getCurrentTrials() {
		return this.currentTrials;
	}

}