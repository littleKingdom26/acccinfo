package info.team23h.acc.controller.admin;

import info.team23h.acc.service.PlayerService;
import info.team23h.acc.service.RecordService;
import info.team23h.acc.vo.PlayerSearch;
import info.team23h.acc.vo.PlayerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@Autowired
	RecordService recordService;

	@GetMapping("/player/main")
	public String playerList(HttpServletRequest request, Model model,
							 @ModelAttribute("playerSearch") PlayerSearch playerSearch){

		List<PlayerVO> driverList = playerService.getPlayerList(playerSearch);
		model.addAttribute("driverList", recordService.getPlayerSkillEvaluatorList(driverList));

		return "/admin/player/main";
	}
}
