package samples.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import samples.json.jackson.dto.JsonFileData;

/**
 * JacksonでJSONファイルを読み込むサンプルコードです.<br>
 * JSON → Javaオブジェクト（デシリアライズ）
 *
 * <p>JSONに対応するDTOクラスを用意し、Jacksonの {@code ObjectMapper#readValue()} で
 * JSONをJavaオブジェクトに読み込みます.（データバインディング）
 */
public class ReadJsonMain {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// JSONとJavaオブジェクトをマッピングするJacksonのマッパー
		ObjectMapper mapper = new ObjectMapper();

		// JSONデータを格納するオブジェクト
		JsonFileData jsonFile = null;

		// ObjectMapper#readValue() でJSONファイルをJavaオブジェクトに読み込む（データバインディング）
		// 読み込むJSONファイルは、Mainクラスと同じパッケージ配下の car_owner.json
		// 上記を Class.getResourceAsStream() で指定する
		try {
			jsonFile = mapper.readValue(
					ReadJsonMain.class.getResourceAsStream("car_owner.json"), JsonFileData.class);
		} catch (Exception e) {
			System.err.println("JSONファイルの読み込みに失敗しました。");
			throw e;
		}

		// 読み込んだJSONファイルの内容を表示する
		// JSONファイルの内容は JsonFileData オブジェクトにバインディングされている
		System.out.println("オーナー情報：");
		System.out.println("name: " + jsonFile.owner.name);
		System.out.println("age: " + jsonFile.owner.age);

		System.out.println("所有車情報：");
		System.out.println("maker: " + jsonFile.owner.car.maker);
		System.out.println("name: " + jsonFile.owner.car.name);
		System.out.println("color: " + jsonFile.owner.car.color);
	}

}
