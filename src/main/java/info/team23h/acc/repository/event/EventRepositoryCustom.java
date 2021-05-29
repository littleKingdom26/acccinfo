package info.team23h.acc.repository.event;

import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;

import java.util.List;

public interface EventRepositoryCustom {
	List<EventResultVO> findByEventList(TeamScoreSaveVO teamScoreSaveVO);

	List<EventResultVO> findByEventRanker(Long eventInfoSeq);
}
