package info.team23h.acc.vo.front.video;

import info.team23h.acc.entity.bbs.Bbs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import javax.persistence.Transient;

@Getter
@Setter
public class VideoPageResultVO {

	@ApiModelProperty(value = "게시물 시퀀스", name = "seq")
	private Long seq;

	@ApiModelProperty(value = "게시물 이름 시퀀스", name = "nameSeq")
	private Long nameSeq;

	@ApiModelProperty(value = "게시물 타이틀", name = "title")
	private String title;

	@ApiModelProperty(value = "등록자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "비디오 url", name = "videoUrl")
	private String videoUrl;

	@ApiModelProperty(value = "댓글 카운터 ", name = "commentCount")
	private int commentCount;

	@Transient
	private Link _link;

	public VideoPageResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.nameSeq = bbs.getTbBbsName().getSeq();
		this.regId = bbs.getRegId();
		this.videoUrl = bbs.getContent();
		this.title = bbs.getTitle();
		this._link = bbs.get_link();
		this.commentCount = bbs.getBbsCommentList().size();
		this._link = bbs.get_link();
	}

}
