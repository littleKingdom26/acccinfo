package info.team23h.acc.service.bbs;

import info.team23h.acc.vo.bbs.AdminBbsNameResultVO;

public interface BbsNameService {

	AdminBbsNameResultVO findById(Long nameSeq);
}
