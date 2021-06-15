package samples.object_serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import samples.object_serialization.dto.CarOwner;

/**
 * メインクラスです.
 *
 * <p>オブジェクトの直列化に関するサンプルコードです.
 *
 * <p>オブジェクトをシリアライズしファイルに保存します.<br>
 * また、シリアライズしたファイルをオブジェクトに復元（デシリアライズ）します.
 */
public class MyApp {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ----------------------------------------------
		// インスタンスの直列化と保存（シリアライズ）
		// ----------------------------------------------

		// シリアライズ対象のオブジェクト
		CarOwner co = new CarOwner();
		co.name = "Tom";
		co.age = 35;
		co.myCar.maker = "マツダ";
		co.myCar.name = "MAZDA3";
		co.myCar.color = "red";

		// try-with-resource文
		// close()処理は不要
		try (
				// オブジェクトをシリアライズするストリーム
				ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream("out/car_owner.dat"));
		) {
			// オブジェクトをバイトデータにシリアライズし、ファイル保存
			oos.writeObject(co);
			oos.flush();

			System.out.println("シリアライズ完了");
		} catch (IOException e) {
			throw new Exception("オブジェクトのシリアライズに失敗しました。", e);
		}

		// ----------------------------------------------
		// ファイルからインスタンスを復元（デシリアライズ）
		// ----------------------------------------------

		// try-with-resource文
		// close()処理は不要
		try (
				// オブジェクトをデシリアライズするストリーム
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("out/car_owner.dat"));
		) {
			// ファイルからオブジェクトを復元
			CarOwner co2 = (CarOwner) ois.readObject();

			System.out.println("デシリアライズ完了");
			System.out.println(co2);
		} catch (IOException e) {
			throw new Exception("オブジェクトのデシリアライズに失敗しました。", e);
		}
	}

}
