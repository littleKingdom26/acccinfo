package info.team23h.acc.restController.admin;

import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.handicap.HandicapService;
import info.team23h.acc.service.score.ScoreService;
import info.team23h.acc.vo.event.EventInfoVO;
import info.team23h.acc.vo.handicap.HandicapInfoVO;
import info.team23h.acc.vo.score.ScoreInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/admin/event")
public class AdminEventRestController {

	@Autowired
	ScoreService scoreService;

	@Autowired
	HandicapService handicapService;

	@Autowired
	EventService eventService;

	/**
	 * 점수 상세 정보 삭제
	 * @param scoreInfoSeq
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/scoreInfo/del/{scoreInfoSeq}")
	@Transactional
	public HashMap<String, Object> scoreInfoDel(@PathVariable("scoreInfoSeq") int scoreInfoSeq) throws Exception {
		ScoreInfoVO param = new ScoreInfoVO();
		param.setScoreInfoSeq(scoreInfoSeq);
		HashMap<String, Object> result = scoreService.delScoreInfo(param);
		return result;
	}


	/**
	 * 핸디탭 정보 삭제
	 * @param handicapInfoSeq
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/handicapInfo/del/{handicapInfoSeq}")
	@Transactional
	public HashMap<String, Object> handicapInfoDel(@PathVariable("handicapInfoSeq") int handicapInfoSeq) throws Exception {
		HandicapInfoVO param = new HandicapInfoVO();
		param.setHandicapInfoSeq(handicapInfoSeq);
		HashMap<String, Object> result = handicapService.delHandicapInfo(param);
		return result;
	}


	/**
	 * 이벤트 정보 삭제
	 *
	 * @param eventInfoSeq
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/eventInfo/del/{eventInfoSeq}")
	@Transactional
	public HashMap<String, Object> eventInfoDel(@PathVariable("eventInfoSeq") int eventInfoSeq) throws Exception {
		EventInfoVO param = new EventInfoVO();
		param.setEventInfoSeq(eventInfoSeq);
		HashMap<String, Object> result = eventService.delEventInfo(param);
		return result;
	}

	/**
	 *
	 * @param eventInfoVO
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/result/del")
	@Transactional
	public HashMap<String, Object> resultDel(@RequestBody EventInfoVO eventInfoVO) throws Exception {
		HashMap<String, Object> result = eventService.delEvent(eventInfoVO);
		return result;
	}
}
