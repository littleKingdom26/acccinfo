package info.team23h.acc.service;

import info.team23h.acc.vo.EventInfoVO;
import info.team23h.acc.vo.EventMetaVO;
import net.minidev.json.parser.ParseException;

import java.util.HashMap;
import java.util.List;

public interface EventService {
	/**
	 * 이벤트 정보를 조회
	 * @return
	 */
	List<EventInfoVO> getEventInfoList();

	/**
	 * 이벤트 정보 저장
	 * @param eventInfoVO
	 * @return
	 */
	int insertEventInfo(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 정보 삭제
	 * @param param
	 * @return
	 */
	HashMap<String, Object> delEventInfo(EventInfoVO param) throws Exception;

	/**
	 * 이벤트 입력
	 * @param eventInfoVO
	 * @return
	 */
	int setEvent(EventInfoVO eventInfoVO) throws ParseException;

	/**
	 * 이벤트 정보 단건 조회
	 * @param eventInfoVO
	 * @return
	 */
	EventInfoVO getEventInfo(EventInfoVO eventInfoVO);

	/**
	 * 빅 그리드 이벤트 입력
	 * @param eventInfoVO
	 * @return
	 */
	int setEventBigGrid(EventInfoVO eventInfoVO) throws ParseException;

	/**
	 * 라운드별 결과 조회
	 * @param eventInfoVO
	 * @return
	 */
	List<HashMap<String, Object>> getEventRoundResult(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 메타 조회
	 * @param eventInfoVO
	 * @return
	 */
	EventMetaVO getEventMeta(EventInfoVO eventInfoVO);

	/**
	 * 대회 결과 조회
	 * @param eventInfoVO
	 * @return
	 */
	List<HashMap<String, Object>> getEventAllResult(EventInfoVO eventInfoVO);

	/**
	 * 이벤트
	 * 메타 조회
	 * @param eventInfoVO
	 * @return
	 */
	List<EventMetaVO> getEventMetaList(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 라운드  삭제
	 * @param eventInfoVO
	 * @return
	 */
	HashMap<String, Object> delEvent(EventInfoVO eventInfoVO);
}
