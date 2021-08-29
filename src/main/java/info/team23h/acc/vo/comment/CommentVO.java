package info.team23h.acc.vo.comment;

import info.team23h.acc.vo.common.CommonVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVO  extends CommonVO {

	@ApiModelProperty(hidden = true)
	private long seq;
	@ApiModelProperty(value = "게시물시퀀스", name = "bbsSeq")
	private long bbsSeq;
	@ApiModelProperty(value = "게시물 내용", name = "comment")
	private String comment;
	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;
}
