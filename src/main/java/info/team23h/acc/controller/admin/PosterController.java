package info.team23h.acc.controller.admin;

import info.team23h.acc.service.poster.PosterService;
import io.swagger.models.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin/poster")
@RequiredArgsConstructor
public class PosterController {

	final private PosterService posterService;


	@GetMapping("/list")
	public String list(Model model){

//		posterService.findPage()

		return "";
	}




}
