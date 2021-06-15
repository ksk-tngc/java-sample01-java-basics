package samples.object_serialization.dto;

import java.io.Serializable;

/**
 * 車を表すクラスです.
 *
 * <p>{@code Serializable}を実装し、直列化を可能とします.
 */
public class Car implements Serializable {

	/** デシリアライズ（復元）時の不整合を防止するためのシリアルバージョンUID */
	private static final long serialVersionUID = 6632711161515883610L;

	/** 車のメーカ */
	public String maker;

	/** 車名 */
	public String name;

	/** ボディカラー */
	public String color;

	/**
	 * インスタンスの文字列表現です.
	 * @return インスタンスの文字列表現
	 */
	@Override
	public String toString() {
		return String.format("Car [maker=%s, name=%s, color=%s]", maker, name, color);
	}

}
