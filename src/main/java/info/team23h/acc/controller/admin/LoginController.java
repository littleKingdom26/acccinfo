package info.team23h.acc.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController {

	@GetMapping("/login")
	public String login(){
		return "/admin/login/login";
	}
}
