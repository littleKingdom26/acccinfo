package info.team23h.acc.service;

import info.team23h.acc.vo.SearchVO;
import info.team23h.acc.vo.TrackVO;
import info.team23h.acc.vo.WeekVO;

import java.util.List;


public interface WeekService {

	/**
	 * 최근 세션 조회
	 *
	 * @return
	 */
	WeekVO getRecently();

	/**
	 * 새로운 세션 생성
	 * @param trackVO
	 * @param sessionID
	 */
	void setSessionId(TrackVO trackVO, int sessionID);

	/**
	 * 주차 리스트 조회
	 * @return
	 * @param searchVO
	 */
	List<WeekVO> getWeekList(SearchVO searchVO);
}
