package info.team23h.acc.vo.bbs;

import info.team23h.acc.util.SearchHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BbsSearch extends SearchHelper {
	@ApiModelProperty(value="게시판 종류" ,name = "nameSeq")
	private long nameSeq;
	@ApiModelProperty(value = "게시판 종류", name = "nameSeq")
	private long bbsSeq;
	private String schRegUserName = "";
	private String check;

}
