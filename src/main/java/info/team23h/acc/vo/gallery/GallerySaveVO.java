package info.team23h.acc.vo.gallery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class GallerySaveVO {

	private String mainFIleName;

	private String regId;

	private String password;

	MultipartFile[] uploadFile;


}
