package info.team23h.acc.vo.front.video;

import info.team23h.acc.entity.bbs.Bbs;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.Link;

import javax.persistence.Transient;

public class VideoResultVO {

	@ApiModelProperty(value = "게시물 시퀀스", name = "seq")
	private final Long seq;

	@ApiModelProperty(value = "게시물 이름 시퀀스", name = "nameSeq")
	private final Long nameSeq;

	@ApiModelProperty(value = "게시물 타이틀", name = "title")
	private final String title;

	@ApiModelProperty(value = "등록자", name = "regId")
	private final String regId;

	@ApiModelProperty(value = "비밀번호", name = "password")
	private String password;

	@ApiModelProperty(value = "비디오 url", name = "videoUrl")
	private final String videoUrl;

	@ApiModelProperty(value = "댓글 카운터 ", name = "commentCount")
	private final int commentCount;

	@Transient
	private final Link _link;

	public VideoResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.nameSeq = bbs.getTbBbsName()
		                  .getSeq();
		this.regId = bbs.getRegId();
		this.videoUrl = bbs.getContent();
		this.title = bbs.getTitle();
		this._link = bbs.get_link();
		this.commentCount = bbs.getBbsCommentList()
		                       .size();
	}

}
