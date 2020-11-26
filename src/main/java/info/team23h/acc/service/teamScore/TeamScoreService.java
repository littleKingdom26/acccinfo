package info.team23h.acc.service.teamScore;

import info.team23h.acc.vo.team.TeamScoreSaveVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;

import java.util.List;

public interface TeamScoreService {

	void save(TeamScoreSaveVO teamScoreSaveVO) throws Exception;

	List<TeamScoreSearchVO> findAllEventDtGroupBy();
}
