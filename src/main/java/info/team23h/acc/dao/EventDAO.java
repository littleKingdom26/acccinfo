package info.team23h.acc.dao;

import info.team23h.acc.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class EventDAO {

	@Autowired
	private SqlSession sql;

	private final String PREFIX = "eventSql";

	public List<EventInfoVO> getEventInfoList() {
		return sql.selectList(PREFIX+".getEventInfoList");
	}

	public int insertEventInfo(EventInfoVO eventInfoVO) {
		return sql.insert(PREFIX + ".insertEventInfo", eventInfoVO);
	}

	public int delEventInfo(EventInfoVO eventInfoVO) {
		return sql.delete(PREFIX + ".delEventInfo", eventInfoVO);
	}

	public EventInfoVO getEventInfo(EventInfoVO eventInfoVO) {
		return sql.selectOne(PREFIX+".getEventInfo", eventInfoVO);
	}

	public int insertPenalty(PenaltyVO penaltyVO) {
		return  sql.insert(PREFIX+".insertPenalty",penaltyVO);
	}

	public int insertEvent(EventVO eventVO) {
		return sql.insert(PREFIX + ".insertEvent", eventVO);
	}

	public int insertEventSub(EventSubVO eventSubVO) {
		return sql.insert(PREFIX + ".insertEventSub", eventSubVO);
	}

	public List<EventVO> getEventPlayerList(EventInfoVO eventInfoVO) {
		return sql.selectList(PREFIX+".getEventPlayerList", eventInfoVO);
	}

	public int insertEventMeta(EventMetaVO eventMetaVO) {
		return sql.insert(PREFIX+".insertEventMeta", eventMetaVO);
	}

	public List<HashMap<String, Object>> getEventRoundResult(EventInfoVO eventInfoVO) {
		return sql.selectList(PREFIX+".getEventRoundResult",eventInfoVO);
	}

	public EventMetaVO getEventMeta(EventInfoVO eventInfoVO) {
		return sql.selectOne(PREFIX + ".getEventMeta", eventInfoVO);
	}

	public List<EventMetaVO> getEventMetaList(EventInfoVO eventInfoVO) {
		return sql.selectList(PREFIX + ".getEventMetaList", eventInfoVO);
	}

	public List<HashMap<String, Object>> getEventAllResult(EventInfoVO eventInfoVO) {
		return sql.selectList(PREFIX + ".getEventAllResult", eventInfoVO);
	}

	public int delEvent(EventInfoVO eventInfoVO) {
		return sql.delete(PREFIX + ".delEvent", eventInfoVO);
	}

	public int delEventSub(EventInfoVO eventInfoVO) {
		return sql.delete(PREFIX + ".delEventSub", eventInfoVO);
	}

	public int delEventMeta(EventInfoVO eventInfoVO) {
		return sql.delete(PREFIX + ".delEventMeta", eventInfoVO);
	}

	public int delPenalty(EventInfoVO eventInfoVO) {
		return sql.delete(PREFIX + ".delPenalty", eventInfoVO);
	}

	public List<PenaltyVO> getEventPenalty(EventInfoVO eventInfoVO) {
		return sql.selectList(PREFIX+".getEventPenalty", eventInfoVO);
	}

	public List<EventSubVO> getEventSubList(EventSubVO eventSubVO) {
		return sql.selectList(PREFIX + ".getEventSubList", eventSubVO);
	}

	public int selectEventMeta(EventMetaVO eventMetaVO) {
		return sql.selectOne(PREFIX+".selectEventMeta", eventMetaVO);
	}

	public List<HashMap<String, Object>> getEventYearResult(EventInfoVO eventInfoVO) {
		return sql.selectList(PREFIX+".getEventYearResult",eventInfoVO);
	}
}
