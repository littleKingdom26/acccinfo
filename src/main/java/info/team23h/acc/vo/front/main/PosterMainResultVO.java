package info.team23h.acc.vo.front.main;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.poster.Poster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.File;

@Getter
@Setter
public class PosterMainResultVO {

	@ApiModelProperty(value = "포스터_경로", name = "path")
	private String imgPath;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "타입", name = "type")
	private String type;

	@ApiModelProperty(value = "타입명", name = "typeName")
	private String typeName;

	public PosterMainResultVO(Poster poster) {

		this.imgPath = File.separator + "imageView" + File.separator + poster.getFilePath() + File.separator + poster.getFileName();
		this.title = poster.getTitle();
		if(! ObjectUtils.isEmpty(poster.getType())) {
			this.type = poster.getType();
			this.typeName = EnumCode.PosterType.valueOf(type).getValue();

		}else{
			this.type = "";
			this.typeName ="";
		}
	}
}
