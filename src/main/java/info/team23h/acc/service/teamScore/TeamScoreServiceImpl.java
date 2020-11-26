package info.team23h.acc.service.teamScore;

import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.repository.event.EventRepository;
import info.team23h.acc.repository.eventInfo.EventInfoRepository;
import info.team23h.acc.repository.player.PlayerRepository;
import info.team23h.acc.repository.team.TeamRepository;
import info.team23h.acc.repository.teamInfo.TeamInfoRepository;
import info.team23h.acc.repository.teamScore.TeamScoreRepository;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
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
public class TeamScoreServiceImpl implements TeamScoreService{

	final EventRepository eventRepository;
	final TeamRepository teamRepository;
	final PlayerRepository playerRepository;

	final TeamInfoRepository teamInfoRepository;
	final TeamScoreRepository teamScoreRepository;
	final EventInfoRepository eventInfoRepository;

	@Transactional
	@Override
	public void save(TeamScoreSaveVO teamScoreSaveVO) throws Exception {
		// 1. 이벤트 조회
		List<EventResultVO> eventResultList = eventRepository.findByEventList(teamScoreSaveVO);
		eventResultList.forEach(eventResultVO -> {
			//TeamScore
			// 2. 팀원 조회
			Optional<Team> team = teamRepository.findByPlayer(playerRepository.findById(eventResultVO.getPlayerId()).orElse(new Player()));
			if(team.isPresent()){
				try{
					teamScoreSaveVO.setScore(eventResultVO.getScore());
					teamScoreSaveVO.setTeam(team.get());
					teamScoreSaveVO.setRegDt(eventResultVO.getRegDt());
					teamScoreSaveVO.setEventInfo(eventInfoRepository.findById(teamScoreSaveVO.getEventInfoSeq()).orElseThrow(Exception::new));

					// 이미 데이터가 있는지 확인
					Optional<TeamScore> teamScore = teamScoreRepository
							.findByEventInfoAndRoundAndTeam(teamScoreSaveVO.getEventInfo(), teamScoreSaveVO.getRound(), teamScoreSaveVO.getTeam());

					if(teamScore.isPresent()){
						teamScore.get().update(eventResultVO.getScore());
					}else{
						TeamScore save = teamScoreRepository.save(TeamScore.builder().teamScoreSaveVO(teamScoreSaveVO).build());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public List<TeamScoreSearchVO> findAllEventDtGroupBy() {
		List<TeamScoreSearchVO> resultList = teamScoreRepository.findAllEventDtGroupBy();
		return resultList;
	}
}
