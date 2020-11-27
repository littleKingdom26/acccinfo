package info.team23h.acc.repository.teamScore;

import info.team23h.acc.vo.team.TeamScoreResultDetailVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
import info.team23h.acc.vo.team.TeamScoreTeamInfoResultVO;

import java.util.List;

public interface TeamScoreRepositoryCustom {

	List<TeamScoreSearchVO> findAllEventDtGroupBy();

	List<TeamScoreTeamInfoResultVO> findTeamScore(TeamScoreSearchVO teamScoreSearchVO);

	List<TeamScoreResultDetailVO> findByTeamScoreDetail(TeamScoreSearchVO teamScoreSearchVO);
}
