package info.team23h.acc.vo.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

@Getter
@Setter
public class BbsResultPageVO {

	@ApiModelProperty(value = "게시물 시퀀스", name = "seq")
	private Long seq;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "댓글건수", name = "commentCount")
	private int commentCount;

	@ApiModelProperty(value = "링크 정보", name = "_link")
	private Link _link;


	public BbsResultPageVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.title = bbs.getTitle();
		this.regId = bbs.getRegId();
		this.commentCount = bbs.getBbsCommentList().size();
		this._link = bbs.get_link();
	}
}
