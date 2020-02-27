package info.team23h.acc.controller;

import info.team23h.acc.service.RecordService;
import info.team23h.acc.service.TrackService;
import info.team23h.acc.service.ViewService;
import info.team23h.acc.service.WeekService;
import info.team23h.acc.vo.SearchVO;
import info.team23h.acc.vo.ViewVo;
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
	 * The View service.
	 */
	@Autowired
	ViewService viewService;

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
		// 주차별 기록
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		//주차 구하기
		model.addAttribute("weekList", weekService.getWeekList(searchVO));
		// 트랙 구하기
		model.addAttribute("trackList", trackService.getTrackList(searchVO));

		if(session.getAttribute("sessionKey") == null || !session.getId().equals(session.getAttribute("sessionKey"))){
			session.setAttribute("sessionKey", session.getId());
			viewService.updateViewCount();
		}
		ViewVo viewVo = viewService.getViewCount();
		model.addAttribute("viewCount", viewVo.getPageViewCount())
		.addAttribute("todayViewCount", viewVo.getTodayViewCount());
		System.out.println("------------------ 디플로이 한 버전 --------------");
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
		return "ajax/recordPlayerDetail";
	}

}
