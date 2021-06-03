package info.team23h.acc.restController.front.result;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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






	// 클래스


	// 시즌 (년도 , 클래스 들어와야함)

	// 라운드 (시즌seq 와야함)

}
