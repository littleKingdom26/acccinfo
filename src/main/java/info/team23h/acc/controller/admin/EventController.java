package info.team23h.acc.controller.admin;


import info.team23h.acc.service.EventService;
import info.team23h.acc.service.HandicapService;
import info.team23h.acc.service.ScoreService;
import info.team23h.acc.vo.EventInfoVO;
import info.team23h.acc.vo.EventMetaVO;
import info.team23h.acc.vo.HandicapInfoVO;
import info.team23h.acc.vo.ScoreInfoVO;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/event")
public class EventController {

	@Autowired
	ScoreService scoreService;

	@Autowired
	HandicapService handicapService;

	@Autowired
	EventService eventService;

	/* 점수 관리 s */

	/**
	 * 점수 관리 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/scoreInfo")
	public String scoreInfo(Model model){
		// 스코어 정보 조회
		List<ScoreInfoVO> scoreInfoList = scoreService.getScoreInfoList();
		model.addAttribute("scoreInfoList", scoreInfoList);
		return "/admin/event/scoreInfo";
	}


	/**
	 * 점수 저장
	 * @param model
	 * @param scoreInfoVO
	 * @return
	 */
	@PostMapping("/scoreInfo/save")
	public String scoreSave(Model model, @ModelAttribute ScoreInfoVO scoreInfoVO) {
		int cnt = scoreService.insertScoreInfo(scoreInfoVO);
		return "redirect:/admin/event/scoreInfo";
	}


	/**
	 * 점수 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/scoreInfo/{scoreInfoSeq}")
	public String scoreDetail(Model model, @PathVariable("scoreInfoSeq") int scoreInfoSeq) {
		// 스코어 정보 조회
		log.debug("scoreInfoSeq > " + scoreInfoSeq);
		ScoreInfoVO param = new ScoreInfoVO();
		param.setScoreInfoSeq(scoreInfoSeq);
		model.addAttribute("scoreDetail", scoreService.getScore(param));
		model.addAttribute("scoreInfo", scoreService.getScoreInfo(param));
		return "/admin/event/ajax/scoreInfoDetail";
	}

	/* 점수 관리 e */


	/* 핸디캡 관리 s */

	/**
	 * 핸디캡 관리 페이지
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/handicapInfo")
	public String handicapInfo(Model model) {
		// 핸디캡 정보 조회
		List<HandicapInfoVO> handicapInfoList = handicapService.getHandicapInfoList();
		model.addAttribute("handicapInfoList", handicapInfoList);
		return "/admin/event/handicapInfo";
	}


	/**
	 * 핸디캡 저장
	 *
	 * @param model
	 * @param handicapInfoVO
	 * @return
	 */
	@PostMapping("/handicapInfo/save")
	public String handicapSave(Model model,
							@ModelAttribute HandicapInfoVO handicapInfoVO) {
		log.debug("handicapInfoVO.toString() > " + handicapInfoVO.toString());
		int cnt = handicapService.insertHandicapInfo(handicapInfoVO);
		return "redirect:/admin/event/handicapInfo";
	}


	/**
	 * 핸디캡 조회
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/handicapInfo/{handicapInfoSeq}")
	public String handicapDetail(Model model,
							  @PathVariable("handicapInfoSeq") int handicapInfoSeq) {
		// 핸디캡 정보 조회
		HandicapInfoVO param = new HandicapInfoVO();
		param.setHandicapInfoSeq(handicapInfoSeq);
		model.addAttribute("handicapDetail", handicapService.getHandicap(param));
		model.addAttribute("handicapInfo", handicapService.getHandicapInfo(param));
		return "/admin/event/ajax/handicapInfoDetail";
	}

	/* 핸디캡 관리 e */

	/* 대회 관리 s */

	/**
	 * 대회 관리 페이지
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/eventInfo")
	public String eventInfo(Model model) {
		// 스코어 정보 조회
		List<ScoreInfoVO> scoreInfoList = scoreService.getScoreInfoList();
		List<HandicapInfoVO> handicapInfoList = handicapService.getHandicapInfoList();
		List<EventInfoVO> eventInfoList = eventService.getEventInfoList();
		model.addAttribute("scoreInfoList", scoreInfoList);
		model.addAttribute("handicapInfoList", handicapInfoList);
		model.addAttribute("eventInfoList", eventInfoList);
		return "/admin/event/eventInfo";
	}

	@PostMapping("/eventInfo/save")
	public String eventInfoSave(Model model,
							@ModelAttribute EventInfoVO eventInfoVO) {
		int cnt = eventService.insertEventInfo(eventInfoVO);
		return "redirect:/admin/event/eventInfo";
	}

	/* 대회 관리 e */

	/* 기록 입력 s */

	/**
	 * 기록 입력 페이지
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/event")
	public String event(Model model) {
		// 스코어 정보 조회
		List<EventInfoVO> eventInfoList = eventService.getEventInfoList();
		model.addAttribute("eventInfoList", eventInfoList);
		return "/admin/event/event";
	}

	@PostMapping("/event/save")
	@Transactional
	public String evnetSave(MultipartHttpServletRequest request,
							@ModelAttribute EventInfoVO eventInfoVO,
							 Model model) throws IOException, ParseException {
		Iterator<String> fileNames = request.getFileNames();
		MultipartFile file = null;
		String newPath = "";
		if(fileNames.hasNext()){
			file = request.getFile(fileNames.next());

			FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
			byte[] readBuffer = new byte[fileInputStream.available()];
			while (fileInputStream.read(readBuffer)!= -1){}
			String readString = new String(readBuffer);
			/* 특수 문자 및 필요없는 문자 제거*/
			readString = readString.replaceAll(" ", "");
			readString = readString.replaceAll("\\n", "");
			readString = readString.replaceAll("\\u0000", "");

			eventInfoVO.setParserString(readString);

			if("Y".equals(eventInfoVO.getBigGridYn())){
				log.debug("빅그리드 " + "빅그리드");
				int cnt = eventService.setEventBigGrid(eventInfoVO);
			}else{
				log.debug("빅그리드 아님");
				// 1/2부 각각 그리드
				int cnt = eventService.setEvent(eventInfoVO);
			}
			// 빅 그리드
		}
		return "redirect:/admin/event/event";
	}

	/* 기록 입력 e */


	/* 대회 리스트 s */

	/**
	 * 리그 확인 페이지
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/result")
	public String result(Model model,
						 EventInfoVO eventInfo) {
		// 스코어 정보 조회
		List<ScoreInfoVO> scoreInfoList = scoreService.getScoreInfoList();
		List<HandicapInfoVO> handicapInfoList = handicapService.getHandicapInfoList();
		List<EventInfoVO> eventInfoList = eventService.getEventInfoList();

		model.addAttribute("scoreInfoList", scoreInfoList);
		model.addAttribute("handicapInfoList", handicapInfoList);
		model.addAttribute("eventInfoList", eventInfoList);


		return "/admin/event/result";
	}


	/**
	 * 점수 상세 정보 삭제
	 *
	 * @param eventInfoVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/result/search")
	public String search(Model model,EventInfoVO eventInfoVO) {
		log.debug("eventInfoVO.toString() > " + eventInfoVO.toString());
		// 메타 조회
		String page = "/admin/event/ajax/resultRoundTable";
		if(eventInfoVO.getRound() == 0){
			EventInfoVO ev = eventService.getEventInfo(eventInfoVO);
			int round = ev.getRound();
			List<Integer> roundList = new ArrayList<>();
			List<HashMap<String, Object>> roundViewList = new ArrayList<>();
			for(int i = 0; i < round; i++){
				roundList.add(i + 1);
				HashMap<String, Object> viewRound = new HashMap<>();
				viewRound.put("round",(i+1));
				roundViewList.add(viewRound);
			}
			eventInfoVO.setRounds(roundList);
			//전체
			List<HashMap<String, Object>> result = eventService.getEventAllResult(eventInfoVO);
			List<EventMetaVO> eventMeta = eventService.getEventMetaList(eventInfoVO);
			model.addAttribute("result", result);
			model.addAttribute("roundViewList", roundViewList);
			model.addAttribute("eventMeta", eventMeta);
			page = "/admin/event/ajax/resultAllRoundTable";
		}else {
			// 라운드
			EventMetaVO eventMeta = eventService.getEventMeta(eventInfoVO);
			List<HashMap<String,Object>> result = eventService.getEventRoundResult(eventInfoVO);
			model.addAttribute("result",result);
			model.addAttribute("eventMeta", eventMeta);
			model.addAttribute("eventInfoVO", eventInfoVO);
			page = "/admin/event/ajax/resultRoundTable";
		}
		return page;
	}

	/* 대회 리스트 e */
}