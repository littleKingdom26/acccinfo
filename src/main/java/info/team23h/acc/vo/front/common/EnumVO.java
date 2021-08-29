package info.team23h.acc.vo.front.common;

import info.team23h.acc.config.variable.EnumModel;
import lombok.Getter;

@Getter
public class EnumVO {
	private final String key;

	private final String value;

	public EnumVO(EnumModel enumModel) {
		this.key = enumModel.getKey();
		this.value = enumModel.getValue();
	}
}
