package info.team23h.acc.restController.front.timeTrial;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.record.RecordService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.timeTrial.TimeTrialService;
import info.team23h.acc.service.track.TrackService;
import info.team23h.acc.service.week.WeekService;
import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.front.timeTrial.TimeTrialPlayerDetailVO;
import info.team23h.acc.vo.front.timeTrial.TimeTrialResultVO;
import info.team23h.acc.vo.front.timeTrial.TrackResultVO;
import info.team23h.acc.vo.front.timeTrial.WeekResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "Time Trial API", description = "타임트라이얼 api 리스트")
@RequestMapping("/api/timeTrial")
public class TimeTrialRestController {

	final private WeekService weekService;

	final private TrackService trackService;

	final private ResponseService responseService;

	final private TimeTrialService timeTrialService;

	final private RecordService recordService;

	// 주차 구하기
	@ApiOperation(value = "RESULT 년도 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<WeekResultVO>> getWeek(){
		return responseService.getSingleResult(weekService.findWeekList());
	}

	// 서킷 구하기
	@ApiOperation(value="서킷 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n" )
	@GetMapping(value="/track",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<TrackResultVO>> getTrack(){
		return responseService.getSingleResult(trackService.findTrackList());
	}

	// 주차별 데이터
	@ApiOperation(value = "주차 타임트라이얼 기록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week/gt3/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<TimeTrialResultVO>> findWeekTimeTrial(@ApiParam(value = "세션 아이디") @PathVariable(value="sessionId") final Long sessionId){
		final List<TimeTrialResultVO> gt3WeekTimeTrial = timeTrialService.findGt3WeekTimeTrial(sessionId);
		return responseService.getSingleResult(gt3WeekTimeTrial);
	}


	@ApiOperation(value = "드라이버 상세 기록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week/gt3/{playerId}/detail", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<TimeTrialPlayerDetailVO>> findGt3PlayerIdDetail(@ApiParam(value = "플레이어 아이디") @PathVariable(value = "playerId") final String playerId) {
		log.debug("sessionId : {}", playerId);
		return responseService.getSingleResult(timeTrialService.findGt3PlayerIdDetail(playerId));
	}


	@ApiOperation(value = "드라이버 TT Score 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week/gt3/{playerId}/ttScore", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<HashMap<String, Object>> findGt3PlayerTtScore(@ApiParam(value = "플레이어 아이디") @PathVariable(value = "playerId") final String playerId) {
		log.debug("sessionId : {}", playerId);
		SearchVO searchVO = new SearchVO();
		searchVO.setPlayerId(playerId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("TTscore", recordService.playerSkillEvaluator(searchVO));
		return responseService.getSingleResult(result);
	}

	// 주차별 데이터
	@ApiOperation(value = "GT4 주차 타임트라이얼 기록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week/gt4/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<TimeTrialResultVO>> findWeekTimeTrialGt4(@ApiParam(value = "세션 아이디") @PathVariable(value = "sessionId") final Long sessionId) {
		final List<TimeTrialResultVO> gt4WeekTimeTrial = timeTrialService.findGt4WeekTimeTrial(sessionId);
		return responseService.getSingleResult(gt4WeekTimeTrial);
	}


	@ApiOperation(value = "GT4 드라이버 상세 기록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week/gt4/{playerId}/detail", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<TimeTrialPlayerDetailVO>> findGt4PlayerIdDetail(@ApiParam(value = "플레이어 아이디") @PathVariable(value = "playerId") final String playerId) {
		log.debug("sessionId : {}", playerId);
		return responseService.getSingleResult(timeTrialService.findGt4PlayerIdDetail(playerId));
	}

}
