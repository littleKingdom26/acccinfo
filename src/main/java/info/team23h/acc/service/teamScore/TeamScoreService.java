package info.team23h.acc.service.teamScore;

import info.team23h.acc.vo.team.TeamScoreResultVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
import info.team23h.acc.vo.team.TeamScoreTeamInfoResultVO;

import java.util.List;

public interface TeamScoreService {

	void save(TeamScoreSaveVO teamScoreSaveVO) throws Exception;

	List<TeamScoreSearchVO> findAllEventDtGroupBy();

	List<TeamScoreTeamInfoResultVO> findTeamScore(TeamScoreSearchVO teamScoreSearchVO);

	TeamScoreResultVO findTeamScoreDetail(TeamScoreSearchVO teamScoreSearchVO);
}
