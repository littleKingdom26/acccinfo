package info.team23h.acc.repository.teamScore;

import info.team23h.acc.vo.team.TeamScoreSearchVO;

import java.util.List;

public interface TeamScoreRepositoryCustom {

	List<TeamScoreSearchVO> findAllEventDtGroupBy();
}
