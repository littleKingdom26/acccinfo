package info.team23h.acc.vo.bbs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.file.FileResultVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BbsResultVO {

	@Builder
	public BbsResultVO(List<BbsCommentResultVO> commentList, Bbs bbs) {
		this.seq = bbs.getSeq();
		this.commentList = commentList;
		this.title = bbs.getTitle();
		this.content = bbs.getContent();
		this.regId = bbs.getRegId();
		this.regDt = bbs.getRegDt();
		this.fileList = bbs.getBbsFileList()
		                   .stream()
		                   .map(FileResultVO::new)
		                   .collect(Collectors.toList());

		if(this.content.contains("?v=") || this.content.contains("https://youtu.be/")) {
			if(this.content.contains("?v=")) {
				this.youtubeKey = this.content.substring(this.content.indexOf("?v=") + 3);
			} else if(this.content.contains("https://youtu.be/")) {
				this.youtubeKey = this.content.replace("https://youtu.be/", "");
			}
		}

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

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private List<FileResultVO> fileList;

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Long seq;

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String youtubeKey;
}
