package info.team23h.acc.repository.teamScore;

import info.team23h.acc.service.teamScore.TeamScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeamScoreRepositoryTest {

	@Autowired
	TeamScoreRepository teamScoreRepository;

	@Autowired
	TeamScoreService teamScoreService;

	@Test
	public void test(){

		/*TeamScoreSearchVO teamScoreSearchVO = new TeamScoreSearchVO();
		teamScoreSearchVO.setSearchEventDt("2020-11");
		List<TeamScoreTeamInfoResultVO> teamScore = teamScoreRepository.findTeamScore(teamScoreSearchVO);*/


	}

	@Test
	public void 리스트조회_test() {

		/*List<TeamScoreSearchVO> allEventDtGroupBy = teamScoreRepository.findAllEventDtGroupBy();*/

	}

	@Test
	public void 팀_디테일_조회(){
		/*TeamScoreSearchVO teamScoreSearchVO = new TeamScoreSearchVO();
		//teamScoreSearchVO.setSearchEventDt("2020-10");
		teamScoreSearchVO.setTeamInfoSeq(5L);

		teamScoreService.findTeamScoreDetail(teamScoreSearchVO);*/

	}

}