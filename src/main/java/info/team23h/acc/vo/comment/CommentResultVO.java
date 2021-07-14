package info.team23h.acc.vo.comment;

import info.team23h.acc.entity.bbs.BbsComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResultVO {

	@ApiModelProperty(value = "코멘트 시퀀스", name = "seq")
	private Long seq;

	@ApiModelProperty(value = "게시물 시퀀스", name = "bbsSeq")
	private Long bbsSeq;

	@ApiModelProperty(value = "내용", name = "comment")
	private String comment;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

	public CommentResultVO(BbsComment bbsComment) {
		seq = bbsComment.getSeq();
		bbsSeq = bbsComment.getBbs().getSeq();
		comment = bbsComment.getComment();
		regId = bbsComment.getRegId();

	}
}
