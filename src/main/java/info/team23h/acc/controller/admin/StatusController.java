package info.team23h.acc.controller.admin;

import info.team23h.acc.service.StatusService;
import info.team23h.acc.service.TrackService;
import info.team23h.acc.vo.SearchVO;
import info.team23h.acc.vo.StatusSearch;
import info.team23h.acc.vo.StatusVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/status")
public class StatusController {

	@Autowired
	StatusService statusService;

	@Autowired
	TrackService trackService;

	@GetMapping(value = "/carStatus")
	public String carStatus(Model model,
					   @ModelAttribute StatusSearch statusSearch) {

		List<StatusVO> list = statusService.getCarStatus(statusSearch);
		model.addAttribute("list", list);
		SearchVO searchVO = new SearchVO();
		searchVO.setTrackSeq(statusSearch.getTrackSeq());
		model.addAttribute("trackList", trackService.getTrackList(searchVO));
		return "/admin/status/carStatus";

	}


}
