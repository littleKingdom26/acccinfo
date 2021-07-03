package info.team23h.acc.vo.front.gallery;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.file.FileResultVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import javax.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GalleryResultVO {

	@ApiModelProperty(value = "게시물 시퀀스", name = "seq")
	private Long seq;

	@ApiModelProperty(value = "게시물 이름 시퀀스", name = "nameSeq")
	private Long nameSeq;
	@ApiModelProperty(value = "등록자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "비밀번호", name = "password")
	private String password;

	@ApiModelProperty(value = "메인파일이름", name = "mainFileName")
	private String mainFileName;

	@ApiModelProperty(value = "파일 리스트", name = "fileResultList")
	private List<FileResultVO> fileResultList;

	@Transient
	private Link _link;

	public GalleryResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.nameSeq = bbs.getTbBbsName().getSeq();
		this.regId = bbs.getRegId();
		this.mainFileName = bbs.getMainFileName();
		fileResultList = bbs.getBbsFileList().stream().map(FileResultVO::new).collect(Collectors.toList());
		this._link = bbs.get_link();
	}
}
