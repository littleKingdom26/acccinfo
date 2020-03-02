package info.team23h.acc.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BbsVO extends CommonVO {
	private int no;
	private long seq;
	private long nameSeq;
	private String title;
	private String content;
	private String regId;
	private String password;
	private List<CommentVO> commentList;
	private int commentCnt;

	private String commentCntViewer;


}
