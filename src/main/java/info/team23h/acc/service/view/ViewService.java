package info.team23h.acc.service.view;

import info.team23h.acc.vo.common.ViewVo;

public interface ViewService {

	/**
	 * view 카운터 증가
	 * @return
	 */
	int updateViewCount();

	/**
	 * view 카운저 조회
	 * @return
	 */
	ViewVo getViewCount();
}
