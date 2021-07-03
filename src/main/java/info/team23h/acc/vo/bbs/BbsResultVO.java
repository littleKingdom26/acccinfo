package info.team23h.acc.vo.bbs;

import com.fasterxml.jackson.annotation.JsonFormat;
import info.team23h.acc.entity.bbs.Bbs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BbsResultVO {

	@Builder
	public BbsResultVO(List<BbsCommentResultVO> commentList, Bbs bbs) {
		this.commentList = commentList;
		this.title = bbs.getTitle();
		this.content = bbs.getContent();
		this.regId = bbs.getRegId();
		this.regDt = bbs.getRegDt();
	}

	@ApiModelProperty(value = "게시물 댓글", name = "commentList")
	private List<BbsCommentResultVO> commentList;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "내용", name = "content")
	private String content;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "작성일", name = "regDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDt;
}
