package info.team23h.acc.vo.front.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCommonVO {

	@ApiModelProperty(value = "페이지", name = "page", example = "1")
	private int page = 1;

	@ApiModelProperty(value = "사이즈", name = "size", example = "20")
	private int size = 20;
}
