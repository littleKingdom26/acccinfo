package info.team23h.acc.vo.comment;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVO  extends CommonVO {

	private long seq;
	private long bbsSeq;
	private String comment;
	private String regId;
}
