package info.team23h.acc.restController.front.event;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.model.response.HATEOASResult;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.bbs.BbsResultDTO;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import info.team23h.acc.vo.front.common.SearchCommonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "Event API", description = "이벤트 api 리스트")
@RequestMapping("/api/event")
public class EventRestController {

	final ResponseService responseService;

	final BbsService bbsService;


	@ApiOperation(value = "이벤트 목록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<Page<Bbs>> getList(final SearchCommonVO commonVO) {
		log.info("NoticeRestController.getList");

		BbsSearchVO bbsSearchVO = new BbsSearchVO();
		bbsSearchVO.setPage(commonVO.getPage());
		bbsSearchVO.setSize(commonVO.getSize());
		if(ObjectUtils.isEmpty(bbsSearchVO.getNameSeq())){
			bbsSearchVO.setNameSeq(2L); // 공지시항
		}
		final Page<Bbs> byAllPages = bbsService.findByAllPages(bbsSearchVO);
		return responseService.getSingleResult(byAllPages);
	}

	@ApiOperation(value = "이벤트 상세 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/detail/{bbsSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HATEOASResult<BbsResultDTO> getBbsDetail(@PathVariable(value = "bbsSeq") final Long bbsSeq) throws Team23hException {
		log.debug("bbsSeq : {}", bbsSeq);
		BbsResultDTO resultDTO = bbsService.findBySeq(bbsSeq);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventRestController.class).getList(new BbsSearchVO()));
		return responseService.getHATEOASResult(resultDTO, linkTo.withRel("parent"));
	}

}
