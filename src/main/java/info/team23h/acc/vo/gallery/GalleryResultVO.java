package info.team23h.acc.vo.gallery;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.file.FileResultVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GalleryResultVO {

	private Long seq;

	private Long nameSeq;

	private String regId;

	private String password;

	private String mainFileName;

	private List<FileResultVO> fileResultList;

	public GalleryResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.nameSeq = bbs.getTbBbsName().getSeq();
		this.regId = bbs.getRegId();
		this.mainFileName = bbs.getMainFileName();
		fileResultList = bbs.getBbsFileList().stream().map(FileResultVO::new).collect(Collectors.toList());
	}
}
