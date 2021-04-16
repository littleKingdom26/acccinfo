package info.team23h.acc.controller;

import info.team23h.acc.service.banner.BannerService;
import info.team23h.acc.service.record.RecordService;
import info.team23h.acc.service.track.TrackService;
import info.team23h.acc.service.week.WeekService;
import info.team23h.acc.vo.banner.BannerSearch;
import info.team23h.acc.vo.common.SearchVO;
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

	@Autowired
	BannerService bannerService;



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
		// GT 3 주차별 기록
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));

		// GT4 주차별 기록
		model.addAttribute("recordList_GT4",recordService.getRecordDataList_GT4(searchVO));

		//주차 구하기
		model.addAttribute("weekList", weekService.getWeekList(searchVO));
		// 트랙 구하기
		model.addAttribute("trackList", trackService.getTrackList(searchVO));
		// 배너 리스트
		model.addAttribute("bannerList", bannerService.getFrontBannerList(new BannerSearch()));

		return "index";
	}


	@GetMapping("/team23h")
	public String team23h(@ModelAttribute SearchVO searchVO,
						HttpSession session,
						Model model) {
		// TODO 뒷 작업
		searchVO.setTeam23h("Y");
		// GT3 주차별 기록
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));

		// GT4 주차별 기록
		model.addAttribute("recordList_GT4",recordService.getRecordDataList_GT4(searchVO));
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
	@PostMapping("/ajax/recordListAjax")
	public String recordListAjax(@ModelAttribute SearchVO searchVO,Model model) {
		model.addAttribute("recordList", recordService.getRecordDataList(searchVO));
		model.addAttribute("recordList_GT4",recordService.getRecordDataList_GT4(searchVO));
		return "ajax/recordListAjax";
	}

	/**
	 * Record player detail ajax string.
	 *
	 * @param searchVO the search vo
	 * @param model    the model
	 * @return the string
	 */
	@PostMapping("/ajax/recordPlayerDetailAjax")
	public String recordPlayerDetailAjax(@ModelAttribute SearchVO searchVO,
								 Model model) {
		model.addAttribute("playerRecordList", recordService.recordPlayerDetail(searchVO));

		//model.addAttribute("playerRecordList_GT4",recordService.recordPlayerDetail_GT4(searchVO));
		double score = recordService.playerSkillEvaluator(searchVO);
		model.addAttribute("ttScore", (int)Math.round(Math.floor(score)));
		return "ajax/recordPlayerDetail";
	}

	@PostMapping("/ajax/recordCarDetailAjax")
	public String recordCarDetailAjax(@ModelAttribute SearchVO searchVO,
										 Model model) {
		model.addAttribute("playerRecordList", recordService.recordCarDetail(searchVO)).addAttribute("searchVO", searchVO);
		return "ajax/recordCarDetail";
	}

	/**
	 * 달력
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@GetMapping("/calendar")
	public String calendar(@ModelAttribute SearchVO searchVO,
						   Model model) {
		// TODO 삭제 예정
		return "calendar/calendar";
	}
}
