package info.team23h.acc.repository.teamScore;

import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.vo.player.PlayerVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class TeamScoreRepositoryTest {
	@Autowired
	PlayerService playerService;
	@Test
	public void 플레이어_정보_확인(){

		PlayerVO playerVO = new PlayerVO();
		playerVO.setPlayerId("S76561198004892038");
		int playerDetail = playerService.getPlayerDetail(playerVO);
		System.out.println(playerDetail);

		/*TeamScoreSearchVO teamScoreSearchVO = new TeamScoreSearchVO();
		teamScoreSearchVO.setSearchEventDt("2020-11");
		List<TeamScoreTeamInfoResultVO> teamScore = teamScoreRepository.findTeamScore(teamScoreSearchVO);*/
	}

	@Test
	public void 임시_테스트(){
		System.out.println("테스트를 해봅시다.");
	}

}