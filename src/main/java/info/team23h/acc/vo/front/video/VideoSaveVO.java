package info.team23h.acc.vo.front.video;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoSaveVO {

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "비디오 url", name = "videoUrl")
	private String videoUrl;

	@ApiModelProperty(value = "비밀번호", name = "password")
	private String password;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

}
