package info.team23h.acc.vo.bbs;

import info.team23h.acc.entity.bbs.TbBbsName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBbsNameResultVO {

	private String bbsName;

	private Long seq;


	public AdminBbsNameResultVO(TbBbsName tbBbsName) {
		this.bbsName = tbBbsName.getBbsName();
		this.seq  = tbBbsName.getSeq();
	}
}
