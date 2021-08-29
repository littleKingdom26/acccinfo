package info.team23h.acc.vo.bbs;

import info.team23h.acc.entity.bbs.TbBbsName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBbsNameResultVO {

	private String bbsName;

	private Long seq;

	private String bbsType;


	public AdminBbsNameResultVO(TbBbsName tbBbsName) {
		this.bbsName = tbBbsName.getBbsName();
		this.seq  = tbBbsName.getSeq();
		this.bbsType = tbBbsName.getBbsType();
	}
}
