package info.team23h.acc.service.event;

import info.team23h.acc.vo.event.EventInfoVO;
import info.team23h.acc.vo.event.EventMetaVO;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.event.EventSubVO;
import info.team23h.acc.vo.front.main.BeforeLeagueRankerGroupResultVO;
import info.team23h.acc.vo.front.result.ResultAllResultVO;
import info.team23h.acc.vo.front.result.ResultReturnVO;
import info.team23h.acc.vo.front.result.ResultSeasonResultVO;
import info.team23h.acc.vo.front.result.ResultSubResultVO;
import info.team23h.acc.vo.penalty.PenaltyVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
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


	List<EventResultVO> findByEventList(TeamScoreSaveVO teamScoreSaveVO);


	/**
	 * 이전 시즌 랭커 조회
	 * @return
	 */
	List<BeforeLeagueRankerGroupResultVO> getBeforeLeagueRanker();


	/**
	 * 이벤트 년도 목록 조회
	 *
	 * @return the list
	 */
	List<Long> findYearGroup();

	/**
	 * 시즌 조회
	 *
	 * @param year     the year
	 * @param division the division
	 * @return the event season
	 */
	List<ResultSeasonResultVO> getEventSeason(Long year, String division);

	/**
	 * 이벤트 라운드 결과
	 *
	 * @param eventInfoSeq the event info seq
	 * @param round        the round
	 */
	List<ResultReturnVO> findEventResultWithRound(Long eventInfoSeq, Long round);

	/**
	 * 이벤트 플레이어 상세 정보
	 *
	 * @param eventInfoSeq the event info seq
	 * @param round        the round
	 * @param carId        the car id
	 */
	List<ResultSubResultVO> findByEventPlayerDetail(Long eventInfoSeq, Long round, String carId);

	/**
	 * 라운드 all 조회
	 *
	 * @param eventInfoSeq the event info seq
	 */
	List<ResultAllResultVO> findEventResult(Long eventInfoSeq);

	List<ResultAllResultVO> getEventSeasonAll(Long year, String division);
}
