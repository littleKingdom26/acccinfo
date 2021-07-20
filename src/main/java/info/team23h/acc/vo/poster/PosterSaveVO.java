package info.team23h.acc.vo.poster;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PosterSaveVO {

	private String type;

	private String title;

	private MultipartFile file;

}
