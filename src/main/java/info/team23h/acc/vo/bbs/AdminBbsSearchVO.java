package info.team23h.acc.vo.bbs;

import info.team23h.acc.vo.front.common.SearchCommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBbsSearchVO extends SearchCommonVO {

	private String title = "";

	private String regId = "";
}
