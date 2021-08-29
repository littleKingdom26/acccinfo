package info.team23h.acc.repository.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbsRepository extends JpaRepository<Bbs,Long>,BbsRepositoryCustom {

	Page<Bbs> findAllByTbBbsName(TbBbsName bbsName, Pageable pageable);


	Page<Bbs> findAllByTbBbsNameAndTitleContains(TbBbsName bbsName,String title, Pageable pageable);


	Page<Bbs> findAllByTbBbsNameAndTitleContainsAndRegIdContains(TbBbsName bbsName, String title, String regId, Pageable pageable);



}