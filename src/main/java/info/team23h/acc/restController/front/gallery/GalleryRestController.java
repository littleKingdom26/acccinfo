package info.team23h.acc.restController.front.gallery;


import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.model.response.HATEOASResult;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.gallery.GalleryService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import info.team23h.acc.vo.front.common.SearchCommonVO;
import info.team23h.acc.vo.front.gallery.GalleryResultVO;
import info.team23h.acc.vo.front.gallery.GallerySaveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "Gallery API", description = "겔러리 api 리스트")
@RequestMapping("/api/gallery")
public class GalleryRestController {

	final private ResponseService responseService;

	final private GalleryService galleryService;

	final BbsService bbsService;


	/**
	 * 겔러리 저장
	 *
	 * @param gallerySaveVO the gallery save vo
	 * @return the single result
	 * @throws IOException the io exception
	 */
	@ApiOperation(value = "겔러리 저장", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@PostMapping(value="/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<GalleryResultVO> gallerySave(@ModelAttribute GallerySaveVO gallerySaveVO) throws IOException {
		log.debug("fileList : {}", gallerySaveVO.toString());
		return responseService.getSingleResult(galleryService.save(gallerySaveVO));
	}

	@ApiOperation(value = "겔러리 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<Page<GalleryResultVO>> findGalleryList(final SearchCommonVO commonVO){
		log.info("GalleryRestController.findGalleryList");
		BbsSearchVO bbsSearchVO = new BbsSearchVO();
		bbsSearchVO.setPage(commonVO.getPage());
		bbsSearchVO.setSize(commonVO.getSize());
		if(ObjectUtils.isEmpty(bbsSearchVO.getNameSeq())){
			bbsSearchVO.setNameSeq(4L); // 공지시항
		}
		final Page<Bbs> byAllPages = bbsService.findByAllPages(bbsSearchVO);
		final Page<GalleryResultVO> map = byAllPages.map(GalleryResultVO::new);
		return responseService.getSingleResult(map);
	}

	/* 겔러리 상세 */
	@ApiOperation(value="겔러리 상세", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n" )
	@GetMapping("/detail/{seq}")
	public HATEOASResult<GalleryResultVO> findGalleryDetail(@PathVariable(value = "seq") final Long bbsSeq){
		GalleryResultVO resultDTO = bbsService.findByGallerySeq(bbsSeq);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GalleryRestController.class).findGalleryList(new SearchCommonVO()));
		return responseService.getHATEOASResult(resultDTO, linkTo.withRel("parent"));
	}

	/* 이미지 삭제 */
	@ApiOperation(value = "이미지 삭제", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@DeleteMapping("/file/{fileSeq}")
	public CommonResult deleteFile(@PathVariable(value="fileSeq") final Long fileSeq){
		bbsService.deleteFile(fileSeq);
		return responseService.getSuccessResult();
	}



	/* 겔러리 수정 */

	/* 겔러리 삭제 */

	/* 비밀번호 확인 */




}
