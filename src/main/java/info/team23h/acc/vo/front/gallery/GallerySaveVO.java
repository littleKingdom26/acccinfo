package info.team23h.acc.vo.front.gallery;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class GallerySaveVO {

	@ApiModelProperty(value = "메인 파일 명", name = "mainFIleName")
	private String mainFIleName;

	@ApiModelProperty(value = "등록자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "비밀번호", name = "password")
	private String password;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "업로드 파일", name = "uploadFile")
	MultipartFile[] uploadFile;


}
