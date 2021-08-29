package info.team23h.acc.service.bbs;

import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.vo.bbs.AdminBbsNameResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BbsNameServiceImpl implements BbsNameService{

	final private BbsNameRepository bbsNameRepository;

	@Override
	public AdminBbsNameResultVO findById(Long nameSeq) {
		final TbBbsName bbsName = bbsNameRepository.findById(nameSeq)
		                                           .orElse(new TbBbsName());

		return new AdminBbsNameResultVO(bbsName);
	}
}
