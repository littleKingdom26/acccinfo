package info.team23h.acc.controller.admin;

import info.team23h.acc.service.team.TeamService;
import info.team23h.acc.service.teamInfo.TeamInfoService;
import info.team23h.acc.vo.team.TeamSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/team")
@RequiredArgsConstructor
public class TeamController {


	final TeamInfoService teamInfoService;

	final TeamService teamService;


	@GetMapping(value = "/teamInfo")
	public String teamInfo(Model model){
		model.addAttribute("teamInfoList", teamInfoService.findAll());
		return "/admin/team/teamInfo";
	}

	@GetMapping(value = "/team")
	public String team(Model model,@ModelAttribute TeamSearchVO teamSearchVO) {
		model.addAttribute("teamInfoList", teamInfoService.findAll());
		if(teamSearchVO.getTeamInfoSeq() != null && teamSearchVO.getTeamInfoSeq() > 0){
			// 팀원 조회
			log.debug("teamSearchVO.getTeamInfoSeq() > {}", teamSearchVO.getTeamInfoSeq());

		}
		return "/admin/team/team";
	}

}
