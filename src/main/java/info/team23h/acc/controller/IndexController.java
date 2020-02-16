package info.team23h.acc.controller;

import info.team23h.acc.service.RecordService;
import info.team23h.acc.service.TrackService;
import info.team23h.acc.service.WeekService;
import info.team23h.acc.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

	@Autowired
	RecordService recordService;

	@Autowired
	WeekService weekService;

	@Autowired
	TrackService trackService;

	@GetMapping("/")
	public String index(@ModelAttribute SearchVO searchVO,
						Model model) {
		// 주차별 기록
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		//주차 구하기
		model.addAttribute("weekList", weekService.getWeekList(searchVO));
		// 트랙 구하기
		model.addAttribute("trackList", trackService.getTrackList(searchVO));
		return "index";
	}

	@PostMapping("/recordListAjax")
	public String recordListAjax(@ModelAttribute SearchVO searchVO,Model model) {
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		return "ajax/recordListAjax";
	}

	@PostMapping("/recordPlayerDetailAjax")
	public String recordPlayerDetailAjax(@ModelAttribute SearchVO searchVO,
								 Model model) {
		model.addAttribute("playerRecordList", recordService.recordPlayerDetail(searchVO));
		return "ajax/recordPlayerDetail";
	}

}
