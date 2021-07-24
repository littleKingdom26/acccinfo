package info.team23h.acc.vo.front.video;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoUpdateVO {

	@ApiModelProperty(value="seq",name="seq",hidden = true)
	private Long seq;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "비디오 url", name = "videoUrl")
	private String videoUrl;

	@ApiModelProperty(value = "비밀번호", name = "password")
	private String password;

	@Builder
	public VideoUpdateVO(Long seq, String title, String videoUrl, String password) {
		this.seq = seq;
		this.title = title;
		this.videoUrl = videoUrl;
		this.password = password;
	}
}
