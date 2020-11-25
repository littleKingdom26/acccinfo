package info.team23h.acc.service.teamScore;

import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.repository.event.EventRepository;
import info.team23h.acc.repository.player.PlayerRepository;
import info.team23h.acc.repository.team.TeamRepository;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.team.TeamService;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
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



	@Transactional
	@Override
	public void save(TeamScoreSaveVO teamScoreSaveVO) {
		// 1. 이벤트 조회
		List<EventResultVO> eventResultList = eventRepository.findByEventList(teamScoreSaveVO);
		eventResultList.forEach(eventResultVO -> {

			//TeamScore

			Optional<Team> team = teamRepository.findByPlayer(playerRepository.findById(eventResultVO.getPlayerId()).orElse(new Player()));
			// 2. 팀원 조회
			// 3. 1 / 2 번조회한곳에서 플레이어 아이디 같은사람 찾기
			teamScoreSaveVO.setScore(eventResultVO.getScore());



		});


		// 4. 팀스코어 테이블에서 3번에서 조회한 아이디가 있는지 체크 후 있다면 삭제
		// 5. 팀 스코어에 값 입력
	}
}
