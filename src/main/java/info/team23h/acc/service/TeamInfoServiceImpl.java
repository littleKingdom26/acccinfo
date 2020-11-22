package info.team23h.acc.service;

import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.repository.team.TeamInfoRepository;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamInfoServiceImpl implements TeamInfoService {


	final TeamInfoRepository teamInfoRepository;

	@Transactional
	@Override
	public TeamInfo save(TeamInfoSaveVO teamInfoSaveVO) {

		TeamInfo save = teamInfoRepository.save(TeamInfo.builder().teamName(teamInfoSaveVO.getTeamName()).build());
		log.debug("save > {}", save);

		return save;
	}
}
