package info.team23h.acc.vo.poster;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.poster.Poster;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
public class PosterPageResultVO {

	private Long posterKey;

	private String type;

	private String fileName;

	private String filePath;

	private String title;

	private LocalDateTime regDt;

	public String getFullPath() {
		if(!"".equals(fileName)){
			return File.separator + "imageView" + File.separator + filePath + File.separator + fileName;
		}else{
			return "";
		}

	}

	public String getTypeName(){
		return EnumCode.posterType.valueOf(type).getValue();
	}

	public PosterPageResultVO(Poster poster) {
		this.posterKey = poster.getPosterKey();
		this.type = poster.getType();
		this.fileName = poster.getFileName();
		this.filePath = poster.getFilePath();
		this.regDt = poster.getRegDt();
		this.title = poster.getTitle();
	}
}
