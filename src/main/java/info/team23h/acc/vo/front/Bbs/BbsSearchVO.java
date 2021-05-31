package info.team23h.acc.vo.front.Bbs;

import info.team23h.acc.vo.front.common.SearchCommonVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BbsSearchVO extends SearchCommonVO {

	@ApiModelProperty(value = "게시판종류 (1 = 공지사항,2=리그,3=자유게시판)", name = "nameSeq")
	private Long nameSeq;

}
