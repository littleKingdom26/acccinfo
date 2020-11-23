package info.team23h.acc.controller;

import info.team23h.acc.service.event.EventService;
import info.team23h.acc.vo.event.EventInfoVO;
import info.team23h.acc.vo.event.EventMetaVO;
import info.team23h.acc.vo.event.EventSubVO;
import info.team23h.acc.vo.penalty.PenaltyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class ResultController {


	@Autowired
	EventService eventService;

	@GetMapping("/result")
	public String resultView(Model model) {
		model.addAttribute("eventInfoList", eventService.getEventInfoList());
		return "result/result";
	}

	@GetMapping("/result/search")
	public String resultSearch(Model model,
							   EventInfoVO eventInfoVO) {
		//model.addAttribute("eventInfoList", eventService.getEventInfoList());
		log.debug("eventInfoVO > " + eventInfoVO);
		String page="";
		if(eventInfoVO.getEventInfoSeq() == 0){
			List<HashMap<String, Object>> result = eventService.getEventYearResult(eventInfoVO);
			for(int i = 0; i < result.size(); i++){
				result.get(i).put("RANK",(i+1));
			}
			model.addAttribute("result",result);
			model.addAttribute("totalDriver", result.size());
			page = "result/ajax/resultYearSearchAjax";
		}else{
			if(eventInfoVO.getRound() == 0){
				EventInfoVO ev = eventService.getEventInfo(eventInfoVO);
				int round = ev.getRound();
				List<Integer> roundList = new ArrayList<>();
				List<HashMap<String, Object>> roundViewList = new ArrayList<>();
				for(int i = 0; i < round; i++){
					roundList.add(i + 1);
					HashMap<String, Object> viewRound = new HashMap<>();
					viewRound.put("round", (i + 1));
					roundViewList.add(viewRound);
				}
				eventInfoVO.setRounds(roundList);
				//전체
				List<HashMap<String, Object>> result = eventService.getEventAllResult(eventInfoVO);
				List<EventMetaVO> eventMeta = eventService.getEventMetaList(eventInfoVO);
				model.addAttribute("result", result);
				model.addAttribute("roundViewList", roundViewList);
				model.addAttribute("eventMeta", eventMeta);
				model.addAttribute("totalDriver", result.size());
				page = "result/ajax/resultAllSearchAjax";
			}else{
				// 라운드
				EventMetaVO eventMeta = eventService.getEventMeta(eventInfoVO);
				List<HashMap<String, Object>> result = eventService.getEventRoundResult(eventInfoVO);

				// 패널티 정보 조회
				List<PenaltyVO> penaltyList = eventService.getEventPenalty(eventInfoVO);

				model.addAttribute("result", result);
				model.addAttribute("eventMeta", eventMeta);
				model.addAttribute("eventInfoVO", eventInfoVO);
				model.addAttribute("penaltyList", penaltyList);
				model.addAttribute("totalDriver", result.size());
				page = "result/ajax/resultRoundSearchAjax";
			}
		}

		return page;
	}

	@GetMapping("/result/lapDetail")
	public String lapDetail(Model model,
							@ModelAttribute EventSubVO eventSubVO) {
		// 렙 정보 조회
		List<EventSubVO> eventSubList = eventService.getEventSubList(eventSubVO);
		model.addAttribute("eventSubList", eventSubList);
		model.addAttribute("eventSubVO", eventSubVO);
		return "result/ajax/lapDetailAjax";
	}

}
