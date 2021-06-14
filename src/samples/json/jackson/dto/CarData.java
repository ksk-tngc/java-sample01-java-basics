package samples.json.jackson.dto;

/**
 * 車の情報を表すDTOクラスです.
 *
 * <p>読み込むJSONの "car" プロパティに対応するクラスです.
 */
public class CarData {

	/** メーカー */
	public String maker;

	/** 車名 */
	public String name;

	/** ボディカラー */
	public String color;

}
