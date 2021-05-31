package info.team23h.acc.repository.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.bbs.BbsSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BbsNameRepositoryCustom {
	Page<Bbs> findPages(PageRequest pageRequest, BbsSearch bbsSearch);
}
