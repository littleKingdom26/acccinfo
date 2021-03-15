package info.team23h.acc.controller;

import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.teamScore.TeamScoreService;
import info.team23h.acc.vo.team.TeamScoreResultVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
import info.team23h.acc.vo.team.TeamScoreTeamInfoResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class TeamResultController {

	@Autowired
	EventService eventService;

	@Autowired
	TeamScoreService teamScoreService;

	@GetMapping("/teamResult")
	public String teamResult(Model model){
		// 팀 이벤트 기록
		model.addAttribute("eventList", teamScoreService.findAllEventDtGroupBy());
		return "teamResult/result";
	}

	@GetMapping("/teamResult/search")
	public String teamResultSearch(Model model, TeamScoreSearchVO teamScoreSearchVO){
		log.debug("teamScoreSearchVO > {}", teamScoreSearchVO);
		List<TeamScoreTeamInfoResultVO> teamScoreResultList = teamScoreService.findTeamScore(teamScoreSearchVO);
		model.addAttribute("teamScoreResultList", teamScoreResultList);
		return "teamResult/ajax/teamResultSearchAjax";
	}

	@GetMapping("/teamResult/teamResultDetail")
	public String teamResultDetail(Model model, TeamScoreSearchVO teamScoreSearchVO){
		TeamScoreResultVO teamScoreResultVO = teamScoreService.findTeamScoreDetail(teamScoreSearchVO);
		model.addAttribute("teamScoreResultVO", teamScoreResultVO);
		return "teamResult/ajax/teamResultDetailAjax";
	}


}
