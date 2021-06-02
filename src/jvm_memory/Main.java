package jvm_memory;

/**
 * メインクラス.
 *
 * <p>{@link Runtime}クラスのサンプルコード.
 * JVMの現在のメモリ状態を表示する.
 */
public class Main {
	/**
	 * メインメソッド.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// 現在のメモリ総容量（現在JVMが確保しているメモリ容量）
		// JVM内のメモリが不足しそうな場合、maxMemoryを上限とし、OSからの追加割当により増加する
		long total = Runtime.getRuntime().totalMemory();

		// 残りメモリ容量
		// totalMemoryを上限とした現在の空き容量
		long free = Runtime.getRuntime().freeMemory();

		// 現在のメモリ使用量
		long used = total - free;

		// メモリ総容量の限界値（totalMemoryの限界値）
		// OSからの追加割当の限界値で、totalMemoryはこの範囲内で追加割りてされる
		long max = Runtime.getRuntime().maxMemory();

		// メモリの状態をMB単位で表示
		printMemoryStatusInMb("メモリ総容量の限界値", max);
		printMemoryStatusInMb("現在のメモリ総容量", total);
		printMemoryStatusInMb("現在のメモリ使用量", used);
		printMemoryStatusInMb("残りメモリ容量", free);
	}

	/**
	 * メモリの状態をMB単位で表示する.
	 *
	 * @param desc メモリ数値の説明
	 * @param inByte メモリ数値（Byte単位）
	 */
	private static void printMemoryStatusInMb(String desc, long inByte) {
		// MBに換算
		double mb = inByte / 1024.0 / 1024.0;

		// メモリの状態をMB単位で表示
		System.out.println(
				String.format("%s: %.3f MB", desc, mb));
	}
}
