package info.team23h.acc.restController.front.reviewReqeust;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.reviewReqeust.ReviewRequestService;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestResultVO;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestSaveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviewRequest")
@Api(tags = "review Reqeust API", description = "심의 요청 api 리스트")
public class ReviewRequestRestController {

	final private ReviewRequestService reviewRequestService;

	final private ResponseService responseService;


	@ApiOperation(value = "심의 신청 저장 ", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<ReviewRequestResultVO> save(@RequestBody final ReviewRequestSaveVO reviewRequestSaveVO){
		ReviewRequestResultVO result = reviewRequestService.save(reviewRequestSaveVO);
		return responseService.getSingleResult(result);
	}





}
