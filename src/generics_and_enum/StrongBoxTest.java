package generics_and_enum;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * {@link StrongBox} のテストクラスです。
 */
public class StrongBoxTest {

	// ----------------------------------------------------
	// put()のテスト
	// ----------------------------------------------------

	/**
	 * 任意の型のデータをStrongBoxインスタンスに格納できること
	 */
	@Test
	public void putテスト1() {

		StrongBox<String> sb1 = new StrongBox<>(StrongBox.KeyType.BUTTON);
		sb1.put("文字列");

		StrongBox<Integer> sb2 = new StrongBox<>(StrongBox.KeyType.BUTTON);
		sb2.put(12345);

	}

	// ----------------------------------------------------
	// get()のテスト
	// ----------------------------------------------------

	/**
	 * 鍵の種類で定められた必要試行回数に達するまでnullが返されること
	 */
	@Test
	public void getテスト1() {

		// 南京錠の金庫。必要試行回数 = 1,024
		StrongBox<String> s1 = new StrongBox<>(StrongBox.KeyType.PADLOCK);
		String data1 = "[格納データ]";
		s1.put(data1);

		// 1023回まではnullが返る
		for (int i = 1; i <= 1023; i++) {
			assertNull(s1.get());
		}

		// 現在の試行回数を表示
		System.out.println(s1.getCurrentTrials());

		// 1024回目のget()から格納データが返る
		assertEquals(s1.get(), data1);

	}

	/**
	 * 格納したデータをキャストなしに取得できること
	 */
	@Test
	public void getテスト2() {

		// String型を格納
		StrongBox<String> s1 = new StrongBox<>(StrongBox.KeyType.FINGER);
		s1.put("文字列");

		// Integer型を格納
		StrongBox<Integer> s2 = new StrongBox<>(StrongBox.KeyType.FINGER);
		s2.put(123);

		// 鍵の種類で定められた必要試行回数+1回ループ
		// （必要試行回数に達しないとnullになる）
		String getData1 = null;
		Integer getData2 = null;

		for (int i = 1; i < StrongBox.KeyType.FINGER.getTrials() + 1; i++) {
			getData1 = s1.get();
			getData2 = s2.get();
		}

		// 確認
		assertEquals("文字列", getData1);
		assertEquals(123, getData2.intValue());

	}

}