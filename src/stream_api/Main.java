package stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * メインクラスです。
 */
public class Main {

	/**
	 * メインメソッドです。
	 * <p>
	 * StreamAPIのサンプルコードです。
	 *
	 * @param args
	 * @see Stream
	 */
	public static void main(String[] args) {

		// -----------------------------------------------------------------
		// Streamの取得
		// コレクションや配列からStreamを取得する
		// -----------------------------------------------------------------

		// コレクションの場合 -> コレクション#stream()
		List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C"));
		Stream<String> stringListStream = stringList.stream();

		// 配列の場合(参照型) -> Arrays.stream(配列)
		String[] stringArray = { "A", "B", "C" };
		Stream<String> stringArrayStream = Arrays.stream(stringArray);

		// 配列の場合(プリミティブ型) -> Arrays.stream(配列)
		int[] intArray = { 1, 2, 3 };
		IntStream intArrayStream = Arrays.stream(intArray); // プリミティブ型専用のStreamが返される(IntStream)

		// -----------------------------------------------------------------
		// コレクション(Stream)の要素を表示する
		// forEach()
		// -----------------------------------------------------------------

		System.out.println("----- コレクションの要素を表示する -----");

		List<String> strings = new ArrayList<>(Arrays.asList("a", "b", "c"));
		strings.stream()
				.forEach(s -> System.out.println(s)); // 終端処理：表示

		strings.stream()
				.forEach(System.out::println); // 終端処理：表示 -> forEach()の引数はConsumerなのでprintln()のメソッド参照でもOK

		// -----------------------------------------------------------------
		// 1～5の中で偶数だけを表示する
		// filter(), forEach()
		// -----------------------------------------------------------------

		System.out.println("----- 1～5の中で偶数だけを表示する -----");

		List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		nums.stream()
				.filter(num -> num % 2 == 0) // 中間処理：偶数を抽出
				.forEach(num -> System.out.println(num)); // 終端処理：表示

		// -----------------------------------------------------------------
		// 要素を2倍して表示する
		// map(), forEach()
		// -----------------------------------------------------------------

		System.out.println("----- 要素を2倍して表示する -----");

		List<Integer> nums2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		nums2.stream()
				.map(num -> num * 2) // 中間処理：要素を2倍する
				.forEach(System.out::println); // 終端処理：表示

		// -----------------------------------------------------------------
		// 要素を2倍したコレクションを返す
		// map(), collect()
		// -----------------------------------------------------------------

		System.out.println("----- 要素を2倍したコレクションを返す -----");

		List<Integer> nums3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		// nums3の要素を2倍したコレクションを取得(collect())
		List<Integer> nums4 = nums3.stream()
				.map(num -> num * 2) // 中間処理：要素を2倍する
				.collect(Collectors.toList()); // 終端処理：コレクションを返す

		System.out.println(nums4);

		// -----------------------------------------------------------------
		// 要素を昇順に並べ替えて表示する
		// sorted(), forEach()
		// -----------------------------------------------------------------

		System.out.println("----- 要素を昇順に並べ替えて表示する -----");

		// 方法1
		List<Integer> nums5 = new ArrayList<>(Arrays.asList(3, 1, 5, 2, 4));
		nums5.stream()
				.sorted((x, y) -> Integer.compare(x, y)) // 中間処理：Integer.compare()をコンパレータとして使用する
				.forEach(num -> System.out.println(num)); // 終端処理：表示

		// 方法2
		List<Integer> nums5_2 = new ArrayList<>(Arrays.asList(3, 1, 5, 2, 4));
		nums5_2.stream()
				.sorted() // 中間処理：自然順序で並び替え
				.forEach(System.out::println); // 終端処理：表示

		// 方法3
		List<Integer> nums5_3 = new ArrayList<>(Arrays.asList(3, 1, 5, 2, 4));
		nums5_3.stream()
				.sorted(Comparator.naturalOrder()) // 中間処理：自然順序で並び替え
				.forEach(System.out::println); // 終端処理：表示

		// -----------------------------------------------------------------
		// 要素を降順に並べ替えて表示する
		// sorted(), forEach()
		// -----------------------------------------------------------------

		System.out.println("----- 要素を降順に並べ替えて表示する -----");

		// 方法1
		List<Integer> nums6 = new ArrayList<>(Arrays.asList(3, 1, 5, 2, 4));
		nums6.stream()
				.sorted((x, y) -> Integer.compare(x, y) * -1) // 中間処理：Integer.compare()をコンパレータとして使用する
				.forEach(num -> System.out.println(num)); // 終端処理：表示

		// 方法2
		List<Integer> nums6_2 = new ArrayList<>(Arrays.asList(3, 1, 5, 2, 4));
		nums6_2.stream()
				.sorted(Comparator.reverseOrder()) // 中間処理：自然順序の逆で並び替え
				.forEach(System.out::println); // 終端処理：表示

		// -----------------------------------------------------------------
		// 重複要素を除去して表示する
		// distinct(), forEach()
		// -----------------------------------------------------------------

		System.out.println("----- 重複要素を除去して表示する -----");

		String[] chars = { "A", "B", "A", "C", "a", "C", "B" };
		Arrays.stream(chars)
				.distinct() // 中間処理：重複除去
				.forEach(c -> System.out.println(c)); // 終端処理：表示

		// -----------------------------------------------------------------
		// 10以上の数値を最大3件、昇順で表示する
		// filter(), sorted(), limit(), forEach()
		// -----------------------------------------------------------------

		System.out.println("----- 10以上の数値を最大3件、昇順で表示する -----");

		int[] nums7 = { 20, 4, 5, 10, 12, 9, 100, 30 };
		Arrays.stream(nums7)
				.filter(num -> num >= 10) // 中間処理：10以上を抽出
				.sorted() // 中間処理：昇順でソート
				.limit(3) // 中間処理：上位3件を抽出
				.forEach(num -> System.out.println(num)); // 終端処理：表示

		// -----------------------------------------------------------------
		// 80以上の要素があるかチェックする
		// anyMatch()
		// -----------------------------------------------------------------

		System.out.println("----- 80以上の要素があるかチェックする -----");

		int[] nums8 = { 20, 4, 5, 10, 12, 9, 100, 30 };
		boolean result = Arrays.stream(nums8)
				.anyMatch(num -> num >= 80); // 終端処理：80以上が1つでもあればtrue

		System.out.println(result);

		// -----------------------------------------------------------------
		// 要素の合計を取得する
		// sum()
		// -----------------------------------------------------------------

		System.out.println("----- 要素の合計を取得する -----");

		int[] nums9 = { 10, 10, 10, 10, 10 };
		int sum = Arrays.stream(nums9)
				.sum(); // 終端処理：合計値を取得

		System.out.println(sum);

		// -----------------------------------------------------------------
		// 要素の最大値を取得する
		// max()
		// -----------------------------------------------------------------

		System.out.println("----- 要素の最大値を取得する -----");

		int[] nums10 = { 20, 4, 5, 10, 12, 9, 100, 30 };
		OptionalInt max = Arrays.stream(nums10)
				.max(); // 終端処理：最大要素を取得

		System.out.println(max.getAsInt());

		// -----------------------------------------------------------------
		// 要素の最小値を取得する
		// min()
		// -----------------------------------------------------------------

		System.out.println("----- 要素の最小値を取得する -----");

		List<Integer> nums11 = new ArrayList<>(Arrays.asList(20, 4, 5, 10, 12, 9, 100, 30));
		Optional<Integer> min = nums11.stream()
				.min(Comparator.naturalOrder()); // 終端処理：大小関係をコンパレータで比較し、最小要素を取得

		System.out.println(min.get());

		// -----------------------------------------------------------------
		// 最初の要素を取得する
		// findFirst()
		// -----------------------------------------------------------------

		System.out.println("----- 最初の要素を取得する -----");

		List<Integer> nums12 = new ArrayList<>(Arrays.asList(20, 4, 5, 10, 12, 9, 100, 30));
		Optional<Integer> first = nums12.stream()
				.findFirst(); // 終端処理：最初の要素を取得。要素が無い場合もあるため戻り値はOptionalクラス

		System.out.println(first.get());

	}

}
