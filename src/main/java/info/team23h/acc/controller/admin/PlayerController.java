package info.team23h.acc.controller.admin;

import info.team23h.acc.vo.PlayerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/admin")
public class PlayerController {


	@GetMapping("/player/main")
	public String playerList(HttpServletRequest request, Model model,
							 @ModelAttribute("playerVO") PlayerVO playerVO){

		return "/admin/player/main";
	}
}
