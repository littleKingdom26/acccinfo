package info.team23h.acc.controller.admin;

import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.service.TeamInfoService;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/team")
@RequiredArgsConstructor
public class TeamController {


	final TeamInfoService teamInfoService;


	@GetMapping(value = "/teamInfo")
	public String teamInfo(Model model){
		model.addAttribute("teamInfoList", teamInfoService.findAll());
		return "/admin/team/teamInfo";
	}

	@GetMapping(value = "/team")
	public String team(Model model) {
		model.addAttribute("teamInfoList", teamInfoService.findAll());
		return "/admin/team/team";
	}

}
