package info.team23h.acc.service.team;

import info.team23h.acc.entity.team.Team;
import info.team23h.acc.vo.team.TeamResultVO;
import info.team23h.acc.vo.team.TeamSaveVO;

import java.util.List;

public interface TeamService {
	List<TeamResultVO> findByTeamInfoSeq(Long teamInfoSeq);

	void delete(Long teamSeq) throws Exception;

	TeamResultVO save(TeamSaveVO teamSaveVO) throws Exception;
}
