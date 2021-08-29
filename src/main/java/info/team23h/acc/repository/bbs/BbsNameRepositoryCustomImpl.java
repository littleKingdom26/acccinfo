package info.team23h.acc.repository.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.vo.bbs.BbsSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BbsNameRepositoryCustomImpl extends QuerydslRepositorySupport implements BbsNameRepositoryCustom {
	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public BbsNameRepositoryCustomImpl( ) {
		super(TbBbsName.class);
	}

	@Override
	public Page<Bbs> findPages(PageRequest pageRequest, BbsSearch bbsSearch) {

		return null;
	}
}
