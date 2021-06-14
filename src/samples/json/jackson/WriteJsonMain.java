package samples.json.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import samples.json.jackson.dto.CarData;
import samples.json.jackson.dto.JsonFileData;
import samples.json.jackson.dto.OwnerData;

/**
 * JacksonでJSONファイルを出力するサンプルコードです.<br>
 * Javaオブジェクト → JSON（シリアライズ）
 *
 * <p>JSONに変換するJavaオブジェクトを作成し、
 * Jacksonの {@code ObjectMapper#writeValueAsString()} でJSON文字列（{@code String}）に変換します.
 * また、{@code ObjectMapper#writeValue()} でJSONファイルに出力します.
 */
public class WriteJsonMain {

	/**
	 * メインメソッドです.
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// JSONにシリアライズするDTOオブジェクトを生成
		JsonFileData jsonData = new JsonFileData();
		jsonData.owner = new OwnerData();
		jsonData.owner.name = "山田太郎";
		jsonData.owner.age = 30;
		jsonData.owner.car = new CarData();
		jsonData.owner.car.maker = "マツダ";
		jsonData.owner.car.name = "MAZDA3";
		jsonData.owner.car.color = "赤";

		// JavaオブジェクトとJSONをマッピングするJacksonのマッパー
		ObjectMapper mapper = new ObjectMapper();

		// ----------------------------------------------------------
		// Javaオブジェクト → JSON文字列（String）変換
		// ----------------------------------------------------------
		String jsonString = null;
		try {
			// JavaオブジェクトをJSON文字列に変換（シリアライズ）
			// ObjectMapper#writerWithDefaultPrettyPrinter()でJSON文字列を良い感じに整形する（インデント等）
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData);

			// JSONの整形は enable() で細かく指定も可能
//			mapper.enable(SerializationFeature.INDENT_OUTPUT);
//			jsonString = mapper.writeValueAsString(jsonData);
		} catch (JsonProcessingException e) {
			System.err.println("JavaオブジェクトをJSON文字列に変換時に例外が発生しました。");
			throw e;
		}

		System.out.println(jsonString);

		// ----------------------------------------------------------
		// Javaオブジェクト → JSONファイル出力
		// ----------------------------------------------------------
		try {
			// JavaオブジェクトをJSONファイルに出力（シリアライズ）
			// ObjectMapper#writerWithDefaultPrettyPrinter()でJSON文字列を良い感じに整形する（インデント等）
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("out/jackson_output_sample.json"), jsonData);
			System.out.println("JSONファイル出力完了");
		} catch (IOException e) {
			System.err.println("JSONファイル出力時の例外が発生しました。");
			throw e;
		}
	}

}
