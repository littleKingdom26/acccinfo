package info.team23h.acc.restController.front.result;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.result.ResultAllResultVO;
import info.team23h.acc.vo.front.result.ResultReturnVO;
import info.team23h.acc.vo.front.result.ResultSeasonResultVO;
import info.team23h.acc.vo.front.result.ResultSubResultVO;
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

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "result API", description = "result api 리스트")
@RequestMapping("/api/result")
public class ResultRestController {

	final ResponseService responseService;

	final EventService eventService;


	/**
	 * result 년도 조회
	 *
	 * @return the single result
	 */
	@ApiOperation(value = "RESULT 년도 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/year",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<Long>> getResultYear(){
		return responseService.getSingleResult(eventService.findYearGroup());
	}

	@ApiOperation(value = "SEASON 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/season/{year}/{division}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<ResultSeasonResultVO>> getSeason(@ApiParam(value = "년도") @PathVariable(name="year") Long year , @ApiParam(value = "클래스 구분",example = "DIVISION_1") @PathVariable(name = "division") String division) {
		final List<ResultSeasonResultVO> eventSeason = eventService.getEventSeason(year, division);
		return responseService.getSingleResult(eventSeason);
	}

	@ApiOperation(value = "RESULT 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/{eventInfoSeq}/{round}")
	public SingleResult<List<ResultReturnVO>> getResultWithRound(@ApiParam(value = "eventInfoSeq") @PathVariable(name = "eventInfoSeq") final Long eventInfoSeq,
														@ApiParam(value = "라운드") @PathVariable(name = "round") final Long round) {
		return responseService.getSingleResult(eventService.findEventResultWithRound(eventInfoSeq, round));
	}

	@ApiOperation(value = "RESULT 상세 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/{eventInfoSeq}/{round}/{carId}")
	public SingleResult<List<ResultSubResultVO>> getResultDetail(@ApiParam(value = "eventInfoSeq") @PathVariable(name = "eventInfoSeq") final Long eventInfoSeq,
																 @ApiParam(value = "라운드") @PathVariable(name = "round") final Long round,
																 @ApiParam(value = "Car ID") @PathVariable(name="carId") final String carId){
		return responseService.getSingleResult(eventService.findByEventPlayerDetail(eventInfoSeq, round, carId));
	}

	@ApiOperation(value = "RESULT ALL 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/{eventInfoSeq}")
	public SingleResult<List<ResultAllResultVO>> getResult(@ApiParam(value = "eventInfoSeq") @PathVariable(name = "eventInfoSeq") final Long eventInfoSeq) {
		return responseService.getSingleResult(eventService.findEventResult(eventInfoSeq));
	}


}
