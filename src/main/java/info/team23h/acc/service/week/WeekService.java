package info.team23h.acc.service.week;

import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.front.timeTrial.WeekResultVO;
import info.team23h.acc.vo.track.TrackVO;
import info.team23h.acc.vo.week.WeekVO;

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

	/**
	 * 주차 모든 정보 구하기
	 */
	List<WeekResultVO> findWeekList();

}
