package info.team23h.acc.vo.front.video;

import info.team23h.acc.vo.front.common.SearchCommonVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoSearchVO extends SearchCommonVO {

	@ApiModelProperty(value = "검색어", name = "keyword")
	private String keyword;

}
