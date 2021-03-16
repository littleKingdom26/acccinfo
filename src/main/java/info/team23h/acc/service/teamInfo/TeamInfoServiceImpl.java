package info.team23h.acc.service.teamInfo;

import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.repository.teamInfo.TeamInfoRepository;
import info.team23h.acc.repository.teamScore.TeamScoreRepository;
import info.team23h.acc.vo.team.TeamInfoResultVO;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
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
public class TeamInfoServiceImpl implements TeamInfoService {


	final TeamInfoRepository teamInfoRepository;

	final TeamScoreRepository teamScoreRepository;

	@Override
	@Transactional
	public TeamInfo save(TeamInfoSaveVO teamInfoSaveVO) {
		TeamInfo save = teamInfoRepository.save(TeamInfo.builder().teamName(teamInfoSaveVO.getTeamName()).build());
		return save;
	}

	@Override
	public List<TeamInfoResultVO> findAll() {
		return teamInfoRepository.findAll().stream().map(TeamInfoResultVO::new).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void delete(Long teamInfoSeq) throws Exception {

		TeamInfo teamInfo = teamInfoRepository.findById(teamInfoSeq).orElseThrow(Exception::new);
		List<TeamScore> allByTeamInfo = teamScoreRepository.findAllByTeamInfo(teamInfo);
		teamScoreRepository.deleteAll(allByTeamInfo);

		teamInfoRepository.delete(teamInfo);
	}
}
