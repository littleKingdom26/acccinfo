package info.team23h.acc.restController.front.timeTrial;

import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.track.TrackService;
import info.team23h.acc.service.week.WeekService;
import info.team23h.acc.vo.front.timeTrial.TrackResultVO;
import info.team23h.acc.vo.front.timeTrial.WeekResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	// 주차 구하기
	@ApiOperation(value = "RESULT 년도 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/week",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<WeekResultVO>> getWeek(){
		return responseService.getSingleResult(weekService.findWeekList());
	}

	// 서킷 구하기
	@ApiOperation(value="서킷 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n" )
	@GetMapping(value="/track",produces = MediaType.APPLICATION_JSON_VALUE)
	public CommonResult getTrack(){
		final List<TrackResultVO> trackList = trackService.findTrackList();
		return responseService.getSingleResult(trackList);
	}






}
