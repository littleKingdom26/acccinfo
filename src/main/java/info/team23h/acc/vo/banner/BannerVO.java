package info.team23h.acc.vo.banner;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class BannerVO extends CommonVO {

	/**
	 * 시퀀스
	 */
	private int seq;
	/**
	 * 제목
	 */
	private String title;
	/**
	 * 클릭시 링크
	 */
	private String url;
	/**
	 * 이미지 url
	 */
	private String filePath;
	/**
	 * 사용여부
	 */
	private String useYn;

	/**
	 * 파일 이미지
	 */
	private MultipartFile bannerImg;

}
