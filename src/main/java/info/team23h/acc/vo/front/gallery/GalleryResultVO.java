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

	@ApiModelProperty(value = "게시물 타이틀", name = "title")
	private String title;
	@ApiModelProperty(value = "등록자", name = "regId")
	private String regId;

	@ApiModelProperty(value = "비밀번호", name = "password")
	private String password;

	@ApiModelProperty(value = "메인파일이름", name = "mainFileName")
	private String mainFileName;

	@ApiModelProperty(value = "메인파일경로", name = "mainFilePath")
	private String mainFilePath;

	@ApiModelProperty(value = "파일 리스트", name = "fileResultList")
	private List<FileResultVO> fileResultList;

	@ApiModelProperty(value = "댓글 카운터 ", name = "commentCount")
	private int commentCount;

	@Transient
	private Link _link;

	public GalleryResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.nameSeq = bbs.getTbBbsName().getSeq();
		this.regId = bbs.getRegId();
		this.mainFileName = bbs.getMainFileName();
		this.title = bbs.getTitle();
		fileResultList = bbs.getBbsFileList().stream().map(FileResultVO::new).collect(Collectors.toList());
		this.mainFilePath = fileResultList.stream().filter(fileResultVO -> fileResultVO.getOriFileName().equals(mainFileName)).map(FileResultVO::getFullPath).collect(Collectors.joining());
		this._link = bbs.get_link();
		this.commentCount = bbs.getBbsCommentList().size();
	}
}
