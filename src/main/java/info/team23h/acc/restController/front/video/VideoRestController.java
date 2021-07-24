package info.team23h.acc.restController.front.video;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.config.Team23hRestException;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.model.response.HATEOASResult;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.video.VideoService;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import info.team23h.acc.vo.front.video.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video")
@Api(tags = "video API", description = "비디오 api 리스트")
public class VideoRestController {

	final private ResponseService responseService;

	final private VideoService videoService;

	final private BbsService bbsService;

	final private BCryptPasswordEncoder bCryptPasswordEncoder;


	/**
	 * 비디오 조회
	 *
	 * @param commonVO the common vo
	 * @return the single result
	 */
	@ApiOperation(value = "비디오 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<Page<VideoPageResultVO>> findGalleryList(final VideoSearchVO commonVO) {
		log.info("GalleryRestController.findGalleryList");
		BbsSearchVO bbsSearchVO = new BbsSearchVO();
		bbsSearchVO.setPage(commonVO.getPage());
		bbsSearchVO.setSize(commonVO.getSize());
		if(ObjectUtils.isEmpty(bbsSearchVO.getNameSeq())) {
			bbsSearchVO.setNameSeq(5L); // 비디오
		}
		final Page<Bbs> byAllPages = bbsService.findByAllPages(bbsSearchVO, commonVO.getKeyword());
		final Page<VideoPageResultVO> map = byAllPages.map(VideoPageResultVO::new);
		return responseService.getSingleResult(map);
	}

	/**
	 * 비디오 저장
	 *
	 * @param videoSaveVO the video save vo
	 * @return the single result
	 */
	@ApiOperation(value = "비디오 저장", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<VideoResultVO> videoSave(@RequestBody VideoSaveVO videoSaveVO) {
		log.info("VideoRestController.videoSave");
		return responseService.getSingleResult(videoService.save(videoSaveVO));
	}


	/**
	 * 비디오 상세
	 *
	 * @param bbsSeq the bbs seq
	 * @return the hateoas result
	 */
	@ApiOperation(value = "비디오 상세", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping("/detail/{seq}")
	public HATEOASResult<VideoResultVO> findGalleryDetail(@PathVariable(value = "seq") final Long bbsSeq) {
		try{
			VideoResultVO resultDTO = videoService.findByVideoSeq(bbsSeq);
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VideoRestController.class)
			                                                                     .findGalleryList(new VideoSearchVO()));
			return responseService.getHATEOASResult(resultDTO, linkTo.withRel("parent"));
		}catch(Team23hException e){
			throw new Team23hRestException(e.getMessage());
		}
	}


	/**
	 * 비디오 수정
	 *
	 * @param videoUpdateVO the video update vo
	 * @return the single result
	 * @throws IOException the io exception
	 */
	@ApiOperation(value = "비디오 수정", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@PostMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<VideoResultVO> modify(@RequestBody VideoUpdateVO videoUpdateVO , @PathVariable("seq") final Long seq) throws IOException {
		try {
			videoUpdateVO.setSeq(seq);
			VideoResultVO videoResultVO = videoService.modify(videoUpdateVO);
			return responseService.getSingleResult(videoResultVO);
		} catch(Team23hException e) {
			throw new Team23hRestException(e.getMessage());
		}
	}

	/**
	 * 비밀번호 확인
	 *
	 * @param password the password
	 * @param seq      the seq
	 * @return the common result
	 */
	/* 비밀번호 확인 */
	@ApiOperation(value = "비밀번호 확인", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/password/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommonResult passwordCheck(@RequestParam(value = "password", required = true) final String password, @PathVariable(value = "seq") final Long seq) {

		try {
			final Bbs byId = videoService.findById(seq);
			if(bCryptPasswordEncoder.matches(password, byId.getPassword())) {
				return responseService.getSuccessResult();
			} else {
				throw new Team23hRestException("비밀번호가 다릅니다..");
			}
		} catch(Team23hException e) {
			throw new Team23hRestException(e.getMessage());
		}

	}

	/**
	 * 비디오 삭제
	 *
	 * @param seq the seq
	 * @return the common result
	 */
	/* 겔러리 삭제 */
	@ApiOperation(value = "비디오 삭제", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@DeleteMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommonResult delete(@PathVariable(name = "seq") final Long seq) {
		try {
			videoService.delete(seq);
			return responseService.getSuccessResult();
		} catch(Team23hException e) {
			throw new Team23hRestException(e.getMessage());
		}
	}

}
