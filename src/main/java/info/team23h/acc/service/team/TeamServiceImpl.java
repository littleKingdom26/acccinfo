package info.team23h.acc.service.team;

import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.repository.team.TeamInfoRepository;
import info.team23h.acc.repository.team.TeamRepository;
import info.team23h.acc.vo.team.TeamResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService{

	final TeamRepository teamRepository;

	final TeamInfoRepository teamInfoRepository;

	@Override
	public List<TeamResultVO> findByTeamInfoSeq(Long teamInfoSeq) {
		TeamInfo teamInfo = teamInfoRepository.findById(teamInfoSeq).orElse(new TeamInfo());
		List<TeamResultVO> teamList = teamRepository.findByTeamInfo(teamInfo).stream().map(TeamResultVO::new).collect(Collectors.toList());
		return teamList;
	}
}
