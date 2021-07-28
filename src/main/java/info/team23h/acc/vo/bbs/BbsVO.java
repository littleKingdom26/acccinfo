package info.team23h.acc.vo.bbs;

import info.team23h.acc.vo.comment.CommentVO;
import info.team23h.acc.vo.common.CommonVO;
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

	private String regName;
	private String commentCntViewer;
	
	private String passwordChange;




}
