package info.team23h.acc.controller.admin;

import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.service.team.TeamService;
import info.team23h.acc.service.teamInfo.TeamInfoService;
import info.team23h.acc.service.teamScore.TeamScoreService;
import info.team23h.acc.vo.player.PlayerSearch;
import info.team23h.acc.vo.player.PlayerVO;
import info.team23h.acc.vo.team.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/team")
@RequiredArgsConstructor
public class TeamController {


	final TeamInfoService teamInfoService;

	final TeamService teamService;

	final PlayerService playerService;

	final TeamScoreService teamScoreService;


	@GetMapping(value = "/teamInfo")
	public String teamInfo(Model model){
		model.addAttribute("teamInfoList", teamInfoService.findAll());
		return "/admin/team/teamInfo";
	}

	@GetMapping(value = "/team")
	public String team(Model model,@ModelAttribute TeamSearchVO teamSearchVO) {
		model.addAttribute("teamInfoList", teamInfoService.findAll());
		return "/admin/team/team";
	}

	@GetMapping(value = "/team/search")
	public String teamSearch(Model model, @ModelAttribute TeamSearchVO teamSearchVO) {
		if(teamSearchVO.getTeamInfoSeq() != null && teamSearchVO.getTeamInfoSeq() > 0){
			// 팀원 조회
			List<TeamResultVO> teamList = teamService.findByTeamInfoSeq(teamSearchVO.getTeamInfoSeq());
			model.addAttribute("teamList", teamList);
		}
		return "/admin/team/ajax/teamSearch";
	}

	@GetMapping(value = "/player/search")
	public String playerSearch(Model model, @ModelAttribute PlayerSearch playerSearch) {
		if(!"".equals(playerSearch.getKeyword())){
			List<PlayerVO> playerList = playerService.getPlayerList(playerSearch);
			model.addAttribute("playerList",playerList);
		}
		return "/admin/team/ajax/playerSearch";
	}


	@GetMapping(value = "/teamScore")
	public String teamScore(Model model, @ModelAttribute TeamScoreSearchVO teamScoreSearchVO) {
		List<TeamScoreSearchVO> eventList =  teamScoreService.findAllEventDtGroupBy();
		model.addAttribute("eventList", eventList);
		return "/admin/team/teamScore";
	}

	@GetMapping(value = "/teamScore/search")
	public String teamScoreSearch(Model model, @ModelAttribute TeamScoreSearchVO teamScoreSearchVO) {
		List<TeamScoreTeamInfoResultVO> teamScoreResultList = teamScoreService.findTeamScore(teamScoreSearchVO);
		model.addAttribute("teamScoreResultList", teamScoreResultList);
		return "/admin/team/ajax/teamScoreSearch";
	}


	@GetMapping(value = "/teamScore/detail")
	public String teamScoreDetail(Model model, @ModelAttribute TeamScoreSearchVO teamScoreSearchVO) {
		TeamScoreResultVO teamScoreResultVO =  teamScoreService.findTeamScoreDetail(teamScoreSearchVO);
		model.addAttribute("teamScoreResultVO", teamScoreResultVO);
		return "/admin/team/popup/teamScoreDetail";
	}












}
