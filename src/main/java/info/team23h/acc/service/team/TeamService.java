package info.team23h.acc.service.team;

import info.team23h.acc.vo.team.TeamResultVO;

import java.util.List;

public interface TeamService {
	List<TeamResultVO> findByTeamInfoSeq(Long teamInfoSeq);
}
