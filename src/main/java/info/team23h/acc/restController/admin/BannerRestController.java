package info.team23h.acc.restController.admin;

import info.team23h.acc.service.banner.BannerService;
import info.team23h.acc.vo.banner.BannerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/admin/banner")
public class BannerRestController {

	@Autowired
	BannerService bannerService;

	@PostMapping("/del")
	public HashMap<String, Object> del(@RequestBody BannerVO bannerVO) {
		HashMap<String, Object> result = bannerService.delBanner(bannerVO);
		return result;
	}
}
