package info.team23h.acc.controller;

import info.team23h.acc.service.RecordService;
import info.team23h.acc.service.TrackService;
import info.team23h.acc.service.WeekService;
import info.team23h.acc.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * The type Index controller.
 */
@Slf4j
@Controller
public class IndexController {

	/**
	 * The Record service.
	 */
	@Autowired
	RecordService recordService;

	/**
	 * The Week service.
	 */
	@Autowired
	WeekService weekService;

	/**
	 * The Track service.
	 */
	@Autowired
	TrackService trackService;



	/**
	 * Index string.
	 *
	 * @param searchVO the search vo
	 * @param session  the session
	 * @param model    the model
	 * @return the string
	 */
	@GetMapping("/")
	public String index(@ModelAttribute SearchVO searchVO,
						HttpSession session,
						Model model) {
		searchVO.setTeam23h("N");
		// 주차별 기록
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		//주차 구하기
		model.addAttribute("weekList", weekService.getWeekList(searchVO));
		// 트랙 구하기
		model.addAttribute("trackList", trackService.getTrackList(searchVO));
		return "index";
	}


	@GetMapping("/team23h")
	public String team23h(@ModelAttribute SearchVO searchVO,
						HttpSession session,
						Model model) {
		searchVO.setTeam23h("Y");
		// 주차별 기록
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		//주차 구하기
		model.addAttribute("weekList", weekService.getWeekList(searchVO));
		// 트랙 구하기
		model.addAttribute("trackList", trackService.getTrackList(searchVO));
		return "index";
	}

	/**
	 * Record list ajax string.
	 *
	 * @param searchVO the search vo
	 * @param model    the model
	 * @return the string
	 */
	@PostMapping("/recordListAjax")
	public String recordListAjax(@ModelAttribute SearchVO searchVO,Model model) {
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		return "ajax/recordListAjax";
	}

	/**
	 * Record player detail ajax string.
	 *
	 * @param searchVO the search vo
	 * @param model    the model
	 * @return the string
	 */
	@PostMapping("/recordPlayerDetailAjax")
	public String recordPlayerDetailAjax(@ModelAttribute SearchVO searchVO,
								 Model model) {
		model.addAttribute("playerRecordList", recordService.recordPlayerDetail(searchVO));
		double score = recordService.playerSkillEvaluator(searchVO);
		model.addAttribute("ttScore", Math.round(Math.floor(score)));
		return "ajax/recordPlayerDetail";
	}



}
