package info.team23h.acc.vo.bbs;

import com.fasterxml.jackson.annotation.JsonFormat;
import info.team23h.acc.entity.bbs.BbsComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BbsCommentResultDTO {

	@ApiModelProperty(value = "댓글", name = "comment")
	private String comment;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "작성일", name = "regDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDt;

	public BbsCommentResultDTO(BbsComment bbsComment) {
		this.comment = bbsComment.getComment();
		this.regId = bbsComment.getRegId();
		this.regDt = bbsComment.getRegDt();
	}
}
