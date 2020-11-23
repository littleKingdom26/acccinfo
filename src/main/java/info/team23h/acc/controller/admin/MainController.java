package info.team23h.acc.controller.admin;

import info.team23h.acc.vo.login.LoginUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class MainController {
	@GetMapping("/main")
	public String main(Model model,
					   @AuthenticationPrincipal LoginUserVO loginUserVO){
		log.debug("loginUserVO.getId() > " + loginUserVO.toString());
		return "/admin/main/main";
	}


}
