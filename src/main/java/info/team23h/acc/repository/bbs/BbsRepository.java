package info.team23h.acc.repository.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BbsRepository extends PagingAndSortingRepository<Bbs,Long> {

	Page<Bbs> findAllByTbBbsName(TbBbsName bbsName, Pageable pageable);
}
