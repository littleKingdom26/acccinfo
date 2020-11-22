package info.team23h.acc.controller.admin;

import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.service.TeamInfoService;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/team")
@RequiredArgsConstructor
public class TeamController {


	final TeamInfoService teamInfoService;


	@GetMapping(value = "/teamInsert")
	public String insertViewer(){

		TeamInfoSaveVO teamInfoSaveVO = new TeamInfoSaveVO();
		teamInfoSaveVO.setTeamName("test");
		TeamInfo save = teamInfoService.save(teamInfoSaveVO);

		return "/admin/test";
	}


	@GetMapping(value = "/test")
	public String test() {

		TeamInfoSaveVO teamInfoSaveVO = new TeamInfoSaveVO();
		teamInfoSaveVO.setTeamName("test");
		TeamInfo save = teamInfoService.save(teamInfoSaveVO);

		return "test";
	}

}
