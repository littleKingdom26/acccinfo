package info.team23h.acc.restController.front;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "notice API", description = "공지사항 api 리스트")
@RequestMapping("/api/notice")
public class NoticeRestController {

	final ResponseService responseService;

	final BbsService bbsService;


	@GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<Page<Bbs>> getList(final BbsSearchVO bbsSearch){
		log.debug("bbsSearch : {}", bbsSearch);

		if(ObjectUtils.isEmpty(bbsSearch.getNameSeq())){
			bbsSearch.setNameSeq(1L); // 공지시항
		}
		final Page<Bbs> byAllPages = bbsService.findByAllPages(bbsSearch);
		return responseService.getSingleResult(byAllPages);

	}

}
