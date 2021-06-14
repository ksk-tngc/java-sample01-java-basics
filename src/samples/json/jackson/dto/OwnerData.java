package samples.json.jackson.dto;

/**
 * 車の所有者の情報を表すDTOクラスです.
 *
 * <p>JSONの "owner" プロパティに対応するクラスです.
 */
public class OwnerData {

	/** 所有者の氏名 */
	public String name;

	/** 所有者の年齢 */
	public int age;

	/** 所有する車 */
	public CarData car;

}
