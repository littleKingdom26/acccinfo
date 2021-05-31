package info.team23h.acc.repository.bbs;

import info.team23h.acc.entity.bbs.TbBbsName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbsNameRepository extends JpaRepository<TbBbsName, Long> , BbsNameRepositoryCustom{}
