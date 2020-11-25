package info.team23h.acc.service.team;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.repository.player.PlayerRepository;
import info.team23h.acc.repository.team.TeamInfoRepository;
import info.team23h.acc.repository.team.TeamRepository;
import info.team23h.acc.vo.team.TeamResultVO;
import info.team23h.acc.vo.team.TeamSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService{

	final TeamRepository teamRepository;

	final TeamInfoRepository teamInfoRepository;

	final PlayerRepository playerRepository;

	@Override
	public List<TeamResultVO> findByTeamInfoSeq(Long teamInfoSeq) {
		TeamInfo teamInfo = teamInfoRepository.findById(teamInfoSeq).orElse(new TeamInfo());
		List<TeamResultVO> teamList = teamRepository.findByTeamInfo(teamInfo).stream().map(TeamResultVO::new).collect(Collectors.toList());
		return teamList;
	}

	@Override
	@Transactional
	public void delete(Long teamSeq) throws Exception {
		teamRepository.delete(teamRepository.findById(teamSeq).orElseThrow(Exception::new));

	}

	@Override
	@Transactional
	public TeamResultVO save(TeamSaveVO teamSaveVO) throws Exception {
		Player player = playerRepository.findById(teamSaveVO.getPlayerId()).orElseThrow(Exception::new);
		Optional<Team> checkPlayer = teamRepository.findByPlayer(player);
		if(checkPlayer.isPresent()){
			throw new Team23hException("해당 유저는 팀에 소속 있습니다. 팀명-"+ checkPlayer.get().getTeamInfo().getTeamName()) ;
		}
		TeamInfo teamInfo = teamInfoRepository.findById(teamSaveVO.getTeamInfoSeq()).orElseThrow(Exception::new);
		return new TeamResultVO(teamRepository.save(new Team(player, teamInfo)));
	}

	@Override
	public TeamResultVO findByPlayerId(String playerId) {
		return new TeamResultVO(teamRepository.findByPlayer(playerRepository.findById(playerId).orElse(new Player())).orElse(new Team()));
	}
}
