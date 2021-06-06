package info.team23h.acc.restController.front.result;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.result.ResultSeasonResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	final ResponseService responseService;

	@Autowired
	final EventService eventService;


	/**
	 * result 년도 조회
	 *
	 * @return the single result
	 */
	@ApiOperation(value = "result 년도 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
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
}
