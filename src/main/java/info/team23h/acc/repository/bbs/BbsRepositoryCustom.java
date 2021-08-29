package info.team23h.acc.repository.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.vo.bbs.AdminBbsSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BbsRepositoryCustom {
	/**
	 * 페이지 조회
	 *
	 * @param bbsName     the bbs name
	 * @param search      the search
	 * @param pageRequest the page request
	 * @return the page
	 */
	Page<Bbs> findAdminPage(TbBbsName bbsName, AdminBbsSearchVO search, Pageable pageRequest);
}
