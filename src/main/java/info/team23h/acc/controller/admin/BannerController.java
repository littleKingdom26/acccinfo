package info.team23h.acc.controller.admin;

import info.team23h.acc.service.BannerService;
import info.team23h.acc.vo.BannerSearch;
import info.team23h.acc.vo.BannerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/banner")
public class BannerController {

	@Autowired
	BannerService bannerService;

	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(Model model,BannerSearch bannerSearch) {

		List<BannerVO> list = bannerService.getBannerList(bannerSearch);
		model.addAttribute("list", list);
		return "/admin/banner/main";

	}

	@PostMapping(value="/save")
	public String save(Model model,@ModelAttribute("bannerVO") BannerVO bannerVO) throws IOException {
		log.debug("bannerVO.toString() > " + bannerVO.toString());
		int cnt = bannerService.saveBanner(bannerVO);
		return "redirect:/admin/banner/main";
	}

}
