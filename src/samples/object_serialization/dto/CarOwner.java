package samples.object_serialization.dto;

import java.io.Serializable;

/**
 * 車の所有者を表すクラスです.
 *
 * <p>{@code Serializable}を実装し、直列化を可能とします.
 */
public class CarOwner implements Serializable {

	/** デシリアライズ（復元）時の不整合を防止するためのシリアルバージョンUID */
	private static final long serialVersionUID = -1401406657022431661L;

	/** 所有者の氏名 */
	public String name;

	/** 所有者の年齢 */
	public int age;

	/** 所有する車 */
	public Car myCar = new Car();

	/**
	 * インスタンスの文字列表現です.
	 * @return インスタンスの文字列表現
	 */
	@Override
	public String toString() {
		return String.format("CarOwner [name=%s, age=%s, myCar=%s]", name, age, myCar);
	}

}
