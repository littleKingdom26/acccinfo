package info.team23h.acc.vo.front.video;

import info.team23h.acc.entity.bbs.Bbs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoResultVO {

	@ApiModelProperty(value = "비디오 시퀀스", name = "seq")
	private Long seq;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "비디오 url", name = "videoUrl")
	private String videoUrl;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

	public VideoResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.title = bbs.getTitle();
		this.videoUrl = bbs.getContent();
		this.regId = bbs.getRegId();
	}

}
