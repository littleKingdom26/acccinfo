package info.team23h.acc.controller.admin;

import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.service.team.TeamService;
import info.team23h.acc.service.teamInfo.TeamInfoService;
import info.team23h.acc.vo.player.PlayerSearch;
import info.team23h.acc.vo.player.PlayerVO;
import info.team23h.acc.vo.team.TeamResultVO;
import info.team23h.acc.vo.team.TeamSearchVO;
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



}
