package info.team23h.acc.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonVO {

	/**  등록일 */
	@ApiModelProperty(hidden = true)
	public String regDt;
	/**
	 * 수정일
	 */
	@ApiModelProperty(hidden = true)
	public String updDt;

}
