package info.team23h.acc.service;

import info.team23h.acc.vo.EventInfoVO;
import info.team23h.acc.vo.EventMetaVO;
import info.team23h.acc.vo.EventSubVO;
import info.team23h.acc.vo.PenaltyVO;
import net.minidev.json.parser.ParseException;

import java.util.HashMap;
import java.util.List;

/**
 * The interface Event service.
 */
public interface EventService {
	/**
	 * 이벤트 정보를 조회
	 *
	 * @return event info list
	 */
	List<EventInfoVO> getEventInfoList();

	/**
	 * 이벤트 정보 저장
	 *
	 * @param eventInfoVO the event info vo
	 * @return int
	 */
	int insertEventInfo(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 정보 삭제
	 *
	 * @param param the param
	 * @return hash map
	 * @throws Exception the exception
	 */
	HashMap<String, Object> delEventInfo(EventInfoVO param) throws Exception;

	/**
	 * 이벤트 입력
	 *
	 * @param eventInfoVO the event info vo
	 * @return event
	 * @throws ParseException the parse exception
	 */
	int setEvent(EventInfoVO eventInfoVO) throws ParseException;

	/**
	 * 이벤트 정보 단건 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return event info
	 */
	EventInfoVO getEventInfo(EventInfoVO eventInfoVO);

	/**
	 * 빅 그리드 이벤트 입력
	 *
	 * @param eventInfoVO the event info vo
	 * @return event big grid
	 * @throws ParseException the parse exception
	 */
	int setEventBigGrid(EventInfoVO eventInfoVO) throws ParseException;

	/**
	 * 라운드별 결과 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return event round result
	 */
	List<HashMap<String, Object>> getEventRoundResult(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 메타 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return event meta
	 */
	EventMetaVO getEventMeta(EventInfoVO eventInfoVO);

	/**
	 * 대회 결과 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return event all result
	 */
	List<HashMap<String, Object>> getEventAllResult(EventInfoVO eventInfoVO);

	/**
	 * 이벤트
	 * 메타 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return event meta list
	 */
	List<EventMetaVO> getEventMetaList(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 라운드  삭제
	 *
	 * @param eventInfoVO the event info vo
	 * @return hash map
	 */
	HashMap<String, Object> delEvent(EventInfoVO eventInfoVO);

	/**
	 * 패널티 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return event penalty
	 */
	List<PenaltyVO> getEventPenalty(EventInfoVO eventInfoVO);

	/**
	 * 이벤트 렙 정보 조회
	 *
	 * @param eventSubVO the event sub vo
	 * @return the event sub list
	 */
	List<EventSubVO> getEventSubList(EventSubVO eventSubVO);


	/**
	 * 년 별 결과 조회
	 *
	 * @param eventInfoVO the event info vo
	 * @return the event year result
	 */
	List<HashMap<String, Object>> getEventYearResult(EventInfoVO eventInfoVO);
}
