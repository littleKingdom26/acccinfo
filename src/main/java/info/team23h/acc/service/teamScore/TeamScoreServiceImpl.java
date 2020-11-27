package info.team23h.acc.service.teamScore;

import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.repository.event.EventRepository;
import info.team23h.acc.repository.eventInfo.EventInfoRepository;
import info.team23h.acc.repository.eventMata.EventMetaRepository;
import info.team23h.acc.repository.player.PlayerRepository;
import info.team23h.acc.repository.team.TeamRepository;
import info.team23h.acc.repository.teamInfo.TeamInfoRepository;
import info.team23h.acc.repository.teamScore.TeamScoreRepository;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
	final EventMetaRepository eventMetaRepository;

	@Transactional
	@Override
	public void save(TeamScoreSaveVO teamScoreSaveVO) throws Exception {
		// 1. 이벤트 조회
		List<EventResultVO> eventResultList = eventRepository.findByEventList(teamScoreSaveVO);
		eventResultList.forEach(eventResultVO -> {
			//TeamScore
			// 2. 팀원 조회
			Optional<Team> team = teamRepository.findByPlayerAndDelYn(playerRepository.findById(eventResultVO.getPlayerId()).orElse(new Player()),"N");
			if(team.isPresent()){
				try{
					teamScoreSaveVO.setScore(eventResultVO.getScore());
					teamScoreSaveVO.setTeam(team.get());
					teamScoreSaveVO.setRegDt(eventResultVO.getRegDt());
					teamScoreSaveVO.setEventMeta(eventMetaRepository.findByEventInfoSeqAndRound(teamScoreSaveVO.getEventInfoSeq(), teamScoreSaveVO.getRound())
																	.orElseThrow(Exception::new));
//					teamScoreSaveVO.setEventMeta(eventInfoRepository.findById(teamScoreSaveVO.getEventInfoSeq()).orElseThrow(Exception::new));

					// 이미 데이터가 있는지 확인
					Optional<TeamScore> teamScore = teamScoreRepository
							.findByEventMetaAndTeam(teamScoreSaveVO.getEventMeta(), teamScoreSaveVO.getTeam());

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
		return teamScoreRepository.findAllEventDtGroupBy();
	}

	@Override
	public List<TeamScoreTeamInfoResultVO> findTeamScore(TeamScoreSearchVO teamScoreSearchVO) {
		return teamScoreRepository.findTeamScore(teamScoreSearchVO);
	}

	@Override
	public TeamScoreResultVO findTeamScoreDetail(TeamScoreSearchVO teamScoreSearchVO) {
		TeamScoreResultVO result = new TeamScoreResultVO();

		Optional<TeamInfo> teamInfoOptional = teamInfoRepository.findById(teamScoreSearchVO.getTeamInfoSeq());
		if(teamInfoOptional.isPresent()){
			result.setTeamInfoResultVO(new TeamInfoResultVO(teamInfoOptional.get()));
			result.setTeamScoreResultDetailList(teamScoreRepository.findByTeamScoreDetail(teamScoreSearchVO));
		}
		return result;
	}
}
