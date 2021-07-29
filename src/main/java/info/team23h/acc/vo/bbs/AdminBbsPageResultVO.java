package info.team23h.acc.vo.bbs;

import com.fasterxml.jackson.annotation.JsonFormat;
import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.BbsFile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class AdminBbsPageResultVO {

	private Long seq;

	@ApiModelProperty(value = "댓글 갯수", name = "commentCount")
	private Long commentCount;

	@ApiModelProperty(value = "제목", name = "title")
	private String title;

	@ApiModelProperty(value = "내용", name = "content")
	private String content;

	@ApiModelProperty(value = "작성자", name = "regId")
	private String regId;

	private String mainFilePath;

	private Long no;

	@ApiModelProperty(value = "작성일", name = "regDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDt;

	public String getRegDtStr() {
		if(ObjectUtils.isEmpty(this.regDt)) {
			return "";
		} else {
			return this.regDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		}
	}

	public AdminBbsPageResultVO(Bbs bbs) {
		this.seq = bbs.getSeq();
		this.commentCount = bbs.getBbsCommentList().stream().count();
		this.title= bbs.getTitle();
		this.content = bbs.getContent();
		this.regId = bbs.getRegId();
		this.regDt = bbs.getRegDt();
		if(bbs.getTbBbsName().getBbsType().equals(EnumCode.BbsType.GALLERY.name())) {
			if(! ObjectUtils.isEmpty(bbs.getMainFileName())) {
				final BbsFile bbsFile1 = bbs.getBbsFileList()
				                            .stream()
				                            .filter(bbsFile -> bbsFile.getOriFileName()
				                                                      .equals(bbs.getMainFileName()))
				                            .findFirst()
				                            .get();
				mainFilePath = File.separator + "imageView" + File.separator + bbsFile1.getFilePath() + File.separator + bbsFile1.getFileName();
			}
		}else if(bbs.getTbBbsName().getBbsType().equals(EnumCode.BbsType.VIDEO.name())){
			mainFilePath = "";
		}

	}
}
